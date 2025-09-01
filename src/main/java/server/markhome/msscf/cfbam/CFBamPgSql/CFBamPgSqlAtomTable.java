// Description: Java 11 PostgreSQL Jdbc DbIO implementation for Atom.

/*
 *	server.markhome.msscf.CFBam
 *
 *	Copyright (c) 2020-2025 Mark Stephen Sobkow
 *	
 *
 *	Manufactured by MSS Code Factory 2.13
 */

package server.markhome.msscf.cfbam.CFBamPgSql;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.msscf.msscf.cflib.CFLib.*;
import server.markhome.msscf.cfsec.CFSec.*;
import server.markhome.msscf.cfint.CFInt.*;
import server.markhome.msscf.cfbam.CFBam.*;
import server.markhome.msscf.cfsec.CFSecObj.*;
import server.markhome.msscf.cfint.CFIntObj.*;
import server.markhome.msscf.cfbam.CFBamObj.*;

/*
 *	CFBamPgSqlAtomTable PostgreSQL Jdbc DbIO implementation
 *	for Atom.
 */
public class CFBamPgSqlAtomTable
	implements ICFBamAtomTable
{
	private CFBamPgSqlSchema schema;
	protected PreparedStatement stmtReadBuffByPKey = null;
	protected PreparedStatement stmtReadBuffAll = null;
	protected PreparedStatement stmtLockBuffByPKey = null;
	protected PreparedStatement stmtCreateByPKey = null;
	protected PreparedStatement stmtUpdateByPKey = null;
	protected PreparedStatement stmtDeleteByPKey = null;
	protected PreparedStatement stmtReadAllBuff = null;
	protected PreparedStatement stmtReadDerivedClassCode = null;
	protected PreparedStatement stmtLockDerivedClassCode = null;
	protected PreparedStatement stmtReadAllClassCode = null;
	protected PreparedStatement stmtReadClassCodeByIdIdx = null;
	protected PreparedStatement stmtReadBuffByIdIdx = null;
	protected PreparedStatement stmtReadClassCodeByUNameIdx = null;
	protected PreparedStatement stmtReadBuffByUNameIdx = null;
	protected PreparedStatement stmtReadClassCodeByValTentIdx = null;
	protected PreparedStatement stmtReadBuffByValTentIdx = null;
	protected PreparedStatement stmtReadClassCodeByScopeIdx = null;
	protected PreparedStatement stmtReadBuffByScopeIdx = null;
	protected PreparedStatement stmtReadClassCodeByDefSchemaIdx = null;
	protected PreparedStatement stmtReadBuffByDefSchemaIdx = null;
	protected PreparedStatement stmtReadClassCodeByPrevIdx = null;
	protected PreparedStatement stmtReadBuffByPrevIdx = null;
	protected PreparedStatement stmtReadClassCodeByNextIdx = null;
	protected PreparedStatement stmtReadBuffByNextIdx = null;
	protected PreparedStatement stmtReadClassCodeByContPrevIdx = null;
	protected PreparedStatement stmtReadBuffByContPrevIdx = null;
	protected PreparedStatement stmtReadClassCodeByContNextIdx = null;
	protected PreparedStatement stmtReadBuffByContNextIdx = null;
	protected PreparedStatement stmtMoveUpBuff = null;
	protected PreparedStatement stmtMoveDownBuff = null;
	protected PreparedStatement stmtDeleteByIdIdx = null;
	protected PreparedStatement stmtDeleteByUNameIdx = null;
	protected PreparedStatement stmtDeleteByValTentIdx = null;
	protected PreparedStatement stmtDeleteByScopeIdx = null;
	protected PreparedStatement stmtDeleteByDefSchemaIdx = null;
	protected PreparedStatement stmtDeleteByPrevIdx = null;
	protected PreparedStatement stmtDeleteByNextIdx = null;
	protected PreparedStatement stmtDeleteByContPrevIdx = null;
	protected PreparedStatement stmtDeleteByContNextIdx = null;

	public CFBamPgSqlAtomTable( CFBamPgSqlSchema argSchema ) {
		schema = argSchema;
	}

	public void createAtom( CFSecAuthorization Authorization,
		CFBamAtomBuff Buff )
	{
		final String S_ProcName = "createAtom";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			String ClassCode = Buff.getClassCode();
			long TenantId = Buff.getRequiredTenantId();
			long ScopeId = Buff.getRequiredScopeId();
			Long DefSchemaTenantId = Buff.getOptionalDefSchemaTenantId();
			Long DefSchemaId = Buff.getOptionalDefSchemaId();
			String Name = Buff.getRequiredName();
			String ShortName = Buff.getOptionalShortName();
			String Label = Buff.getOptionalLabel();
			String ShortDescription = Buff.getOptionalShortDescription();
			String Description = Buff.getOptionalDescription();
			String DefaultXmlValue = Buff.getOptionalDefaultXmlValue();
			boolean IsNullable = Buff.getRequiredIsNullable();
			Boolean GenerateId = Buff.getOptionalGenerateId();
			boolean ImplementsPolymorph = Buff.getRequiredImplementsPolymorph();
			Long PrevTenantId = Buff.getOptionalPrevTenantId();
			Long PrevId = Buff.getOptionalPrevId();
			Long NextTenantId = Buff.getOptionalNextTenantId();
			Long NextId = Buff.getOptionalNextId();
			String DbName = Buff.getOptionalDbName();
			Connection cnx = schema.getCnx();
			String sql =
				"select * from " + schema.getLowerDbSchemaName() + ".sp_create_atomdef( ?, ?, ?, ?, ?, ?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + ", "
				+		"?" + " )";
			if( stmtCreateByPKey == null ) {
				stmtCreateByPKey = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtCreateByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtCreateByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtCreateByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtCreateByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtCreateByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtCreateByPKey.setString( argIdx++, ClassCode );
			stmtCreateByPKey.setLong( argIdx++, TenantId );
			stmtCreateByPKey.setLong( argIdx++, ScopeId );
			if( DefSchemaTenantId != null ) {
				stmtCreateByPKey.setLong( argIdx++, DefSchemaTenantId.longValue() );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( DefSchemaId != null ) {
				stmtCreateByPKey.setLong( argIdx++, DefSchemaId.longValue() );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			stmtCreateByPKey.setString( argIdx++, Name );
			if( ShortName != null ) {
				stmtCreateByPKey.setString( argIdx++, ShortName );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( Label != null ) {
				stmtCreateByPKey.setString( argIdx++, Label );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( ShortDescription != null ) {
				stmtCreateByPKey.setString( argIdx++, ShortDescription );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( Description != null ) {
				stmtCreateByPKey.setString( argIdx++, Description );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( DefaultXmlValue != null ) {
				stmtCreateByPKey.setString( argIdx++, DefaultXmlValue );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			stmtCreateByPKey.setBoolean( argIdx++, IsNullable );
			if( GenerateId != null ) {
				stmtCreateByPKey.setBoolean( argIdx++, GenerateId.booleanValue() );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.BOOLEAN );
			}
			stmtCreateByPKey.setBoolean( argIdx++, ImplementsPolymorph );
			if( PrevTenantId != null ) {
				stmtCreateByPKey.setLong( argIdx++, PrevTenantId.longValue() );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( PrevId != null ) {
				stmtCreateByPKey.setLong( argIdx++, PrevId.longValue() );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( NextTenantId != null ) {
				stmtCreateByPKey.setLong( argIdx++, NextTenantId.longValue() );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( NextId != null ) {
				stmtCreateByPKey.setLong( argIdx++, NextId.longValue() );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( DbName != null ) {
				stmtCreateByPKey.setString( argIdx++, DbName );
			}
			else {
				stmtCreateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			resultSet = stmtCreateByPKey.executeQuery();
			if( resultSet.next() ) {
				CFBamAtomBuff createdBuff = unpackAtomResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				Buff.setRequiredTenantId( createdBuff.getRequiredTenantId() );
				Buff.setRequiredScopeId( createdBuff.getRequiredScopeId() );
				Buff.setRequiredId( createdBuff.getRequiredId() );
				Buff.setOptionalDefSchemaTenantId( createdBuff.getOptionalDefSchemaTenantId() );
				Buff.setOptionalDefSchemaId( createdBuff.getOptionalDefSchemaId() );
				Buff.setRequiredName( createdBuff.getRequiredName() );
				Buff.setOptionalShortName( createdBuff.getOptionalShortName() );
				Buff.setOptionalLabel( createdBuff.getOptionalLabel() );
				Buff.setOptionalShortDescription( createdBuff.getOptionalShortDescription() );
				Buff.setOptionalDescription( createdBuff.getOptionalDescription() );
				Buff.setOptionalDefaultXmlValue( createdBuff.getOptionalDefaultXmlValue() );
				Buff.setRequiredIsNullable( createdBuff.getRequiredIsNullable() );
				Buff.setOptionalGenerateId( createdBuff.getOptionalGenerateId() );
				Buff.setRequiredImplementsPolymorph( createdBuff.getRequiredImplementsPolymorph() );
				Buff.setOptionalPrevTenantId( createdBuff.getOptionalPrevTenantId() );
				Buff.setOptionalPrevId( createdBuff.getOptionalPrevId() );
				Buff.setOptionalNextTenantId( createdBuff.getOptionalNextTenantId() );
				Buff.setOptionalNextId( createdBuff.getOptionalNextId() );
				Buff.setRequiredRevision( createdBuff.getRequiredRevision() );
				Buff.setCreatedByUserId( createdBuff.getCreatedByUserId() );
				Buff.setCreatedAt( createdBuff.getCreatedAt() );
				Buff.setUpdatedByUserId( createdBuff.getUpdatedByUserId() );
				Buff.setUpdatedAt( createdBuff.getUpdatedAt() );
				Buff.setOptionalDbName( createdBuff.getOptionalDbName() );
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected a single-record response, " + resultSet.getRow() + " rows selected" );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public String S_sqlSelectAtomDistinctClassCode = null;

	public String getSqlSelectAtomDistinctClassCode() {
		if( S_sqlSelectAtomDistinctClassCode == null ) {
			S_sqlSelectAtomDistinctClassCode =
					"SELECT "
					+		"DISTINCT a809.ClassCode "
					+	"FROM " + schema.getLowerDbSchemaName() + ".valdef AS a809 "
						+	"INNER JOIN " + schema.getLowerDbSchemaName() + ".atomdef a80a ON "
					+		"a80a.TenantId = a809.TenantId "
					+		"AND a80a.Id = a809.Id ";
		}
		return( S_sqlSelectAtomDistinctClassCode );
	}

	public String S_sqlSelectAtomBuff = null;

	public String getSqlSelectAtomBuff() {
		if( S_sqlSelectAtomBuff == null ) {
			S_sqlSelectAtomBuff =
					"SELECT "
					+		"a809.ClassCode, "
					+		"a809.TenantId, "
					+		"a809.Id, "
					+		"a809.ScopeId, "
					+		"a809.defschtentid, "
					+		"a809.defschid, "
					+		"a809.safe_name, "
					+		"a809.short_name, "
					+		"a809.Label, "
					+		"a809.short_descr, "
					+		"a809.descr, "
					+		"a809.dflt_xml_val, "
					+		"a809.IsNullable, "
					+		"a809.GenerateId, "
					+		"a809.ImplementsPolymorph, "
					+		"a809.PrevTenantId, "
					+		"a809.PrevId, "
					+		"a809.NextTenantId, "
					+		"a809.NextId, "
					+		"a80a.DbName, "
					+		"a809.Revision "
				+	"FROM " + schema.getLowerDbSchemaName() + ".valdef AS a809 "
						+	"INNER JOIN " + schema.getLowerDbSchemaName() + ".atomdef a80a ON "
					+		"a80a.TenantId = a809.TenantId "
					+		"AND a80a.Id = a809.Id ";
		}
		return( S_sqlSelectAtomBuff );
	}

	protected CFBamAtomBuff unpackAtomResultSetToBuff( ResultSet resultSet )
	throws SQLException
	{
		final String S_ProcName = "unpackAtomResultSetToBuff";
		int idxcol = 1;
		String classCode = resultSet.getString( idxcol );
		idxcol++;
		CFBamAtomBuff buff;
		if( classCode.equals( "a80a" ) ) {
			buff = schema.getFactoryAtom().newBuff();
		}
		else if( classCode.equals( "a80b" ) ) {
			buff = schema.getFactoryBlobDef().newBuff();
		}
		else if( classCode.equals( "a80c" ) ) {
			buff = schema.getFactoryBlobType().newBuff();
		}
		else if( classCode.equals( "a852" ) ) {
			buff = schema.getFactoryBlobCol().newBuff();
		}
		else if( classCode.equals( "a80d" ) ) {
			buff = schema.getFactoryBoolDef().newBuff();
		}
		else if( classCode.equals( "a80e" ) ) {
			buff = schema.getFactoryBoolType().newBuff();
		}
		else if( classCode.equals( "a853" ) ) {
			buff = schema.getFactoryBoolCol().newBuff();
		}
		else if( classCode.equals( "a815" ) ) {
			buff = schema.getFactoryDateDef().newBuff();
		}
		else if( classCode.equals( "a816" ) ) {
			buff = schema.getFactoryDateType().newBuff();
		}
		else if( classCode.equals( "a854" ) ) {
			buff = schema.getFactoryDateCol().newBuff();
		}
		else if( classCode.equals( "a81c" ) ) {
			buff = schema.getFactoryDoubleDef().newBuff();
		}
		else if( classCode.equals( "a81d" ) ) {
			buff = schema.getFactoryDoubleType().newBuff();
		}
		else if( classCode.equals( "a855" ) ) {
			buff = schema.getFactoryDoubleCol().newBuff();
		}
		else if( classCode.equals( "a81f" ) ) {
			buff = schema.getFactoryFloatDef().newBuff();
		}
		else if( classCode.equals( "a820" ) ) {
			buff = schema.getFactoryFloatType().newBuff();
		}
		else if( classCode.equals( "a858" ) ) {
			buff = schema.getFactoryFloatCol().newBuff();
		}
		else if( classCode.equals( "a823" ) ) {
			buff = schema.getFactoryInt16Def().newBuff();
		}
		else if( classCode.equals( "a824" ) ) {
			buff = schema.getFactoryInt16Type().newBuff();
		}
		else if( classCode.equals( "a859" ) ) {
			buff = schema.getFactoryId16Gen().newBuff();
		}
		else if( classCode.equals( "a856" ) ) {
			buff = schema.getFactoryEnumDef().newBuff();
		}
		else if( classCode.equals( "a857" ) ) {
			buff = schema.getFactoryEnumType().newBuff();
		}
		else if( classCode.equals( "a85c" ) ) {
			buff = schema.getFactoryInt16Col().newBuff();
		}
		else if( classCode.equals( "a825" ) ) {
			buff = schema.getFactoryInt32Def().newBuff();
		}
		else if( classCode.equals( "a826" ) ) {
			buff = schema.getFactoryInt32Type().newBuff();
		}
		else if( classCode.equals( "a85a" ) ) {
			buff = schema.getFactoryId32Gen().newBuff();
		}
		else if( classCode.equals( "a85d" ) ) {
			buff = schema.getFactoryInt32Col().newBuff();
		}
		else if( classCode.equals( "a827" ) ) {
			buff = schema.getFactoryInt64Def().newBuff();
		}
		else if( classCode.equals( "a828" ) ) {
			buff = schema.getFactoryInt64Type().newBuff();
		}
		else if( classCode.equals( "a85b" ) ) {
			buff = schema.getFactoryId64Gen().newBuff();
		}
		else if( classCode.equals( "a85e" ) ) {
			buff = schema.getFactoryInt64Col().newBuff();
		}
		else if( classCode.equals( "a829" ) ) {
			buff = schema.getFactoryNmTokenDef().newBuff();
		}
		else if( classCode.equals( "a82a" ) ) {
			buff = schema.getFactoryNmTokenType().newBuff();
		}
		else if( classCode.equals( "a85f" ) ) {
			buff = schema.getFactoryNmTokenCol().newBuff();
		}
		else if( classCode.equals( "a82b" ) ) {
			buff = schema.getFactoryNmTokensDef().newBuff();
		}
		else if( classCode.equals( "a82c" ) ) {
			buff = schema.getFactoryNmTokensType().newBuff();
		}
		else if( classCode.equals( "a860" ) ) {
			buff = schema.getFactoryNmTokensCol().newBuff();
		}
		else if( classCode.equals( "a82d" ) ) {
			buff = schema.getFactoryNumberDef().newBuff();
		}
		else if( classCode.equals( "a82e" ) ) {
			buff = schema.getFactoryNumberType().newBuff();
		}
		else if( classCode.equals( "a861" ) ) {
			buff = schema.getFactoryNumberCol().newBuff();
		}
		else if( classCode.equals( "a838" ) ) {
			buff = schema.getFactoryStringDef().newBuff();
		}
		else if( classCode.equals( "a839" ) ) {
			buff = schema.getFactoryStringType().newBuff();
		}
		else if( classCode.equals( "a862" ) ) {
			buff = schema.getFactoryStringCol().newBuff();
		}
		else if( classCode.equals( "a83a" ) ) {
			buff = schema.getFactoryTZDateDef().newBuff();
		}
		else if( classCode.equals( "a83b" ) ) {
			buff = schema.getFactoryTZDateType().newBuff();
		}
		else if( classCode.equals( "a863" ) ) {
			buff = schema.getFactoryTZDateCol().newBuff();
		}
		else if( classCode.equals( "a83c" ) ) {
			buff = schema.getFactoryTZTimeDef().newBuff();
		}
		else if( classCode.equals( "a83d" ) ) {
			buff = schema.getFactoryTZTimeType().newBuff();
		}
		else if( classCode.equals( "a864" ) ) {
			buff = schema.getFactoryTZTimeCol().newBuff();
		}
		else if( classCode.equals( "a83e" ) ) {
			buff = schema.getFactoryTZTimestampDef().newBuff();
		}
		else if( classCode.equals( "a83f" ) ) {
			buff = schema.getFactoryTZTimestampType().newBuff();
		}
		else if( classCode.equals( "a865" ) ) {
			buff = schema.getFactoryTZTimestampCol().newBuff();
		}
		else if( classCode.equals( "a841" ) ) {
			buff = schema.getFactoryTextDef().newBuff();
		}
		else if( classCode.equals( "a842" ) ) {
			buff = schema.getFactoryTextType().newBuff();
		}
		else if( classCode.equals( "a866" ) ) {
			buff = schema.getFactoryTextCol().newBuff();
		}
		else if( classCode.equals( "a843" ) ) {
			buff = schema.getFactoryTimeDef().newBuff();
		}
		else if( classCode.equals( "a845" ) ) {
			buff = schema.getFactoryTimeType().newBuff();
		}
		else if( classCode.equals( "a867" ) ) {
			buff = schema.getFactoryTimeCol().newBuff();
		}
		else if( classCode.equals( "a846" ) ) {
			buff = schema.getFactoryTimestampDef().newBuff();
		}
		else if( classCode.equals( "a847" ) ) {
			buff = schema.getFactoryTimestampType().newBuff();
		}
		else if( classCode.equals( "a868" ) ) {
			buff = schema.getFactoryTimestampCol().newBuff();
		}
		else if( classCode.equals( "a848" ) ) {
			buff = schema.getFactoryTokenDef().newBuff();
		}
		else if( classCode.equals( "a849" ) ) {
			buff = schema.getFactoryTokenType().newBuff();
		}
		else if( classCode.equals( "a869" ) ) {
			buff = schema.getFactoryTokenCol().newBuff();
		}
		else if( classCode.equals( "a84a" ) ) {
			buff = schema.getFactoryUInt16Def().newBuff();
		}
		else if( classCode.equals( "a84b" ) ) {
			buff = schema.getFactoryUInt16Type().newBuff();
		}
		else if( classCode.equals( "a86a" ) ) {
			buff = schema.getFactoryUInt16Col().newBuff();
		}
		else if( classCode.equals( "a84c" ) ) {
			buff = schema.getFactoryUInt32Def().newBuff();
		}
		else if( classCode.equals( "a84d" ) ) {
			buff = schema.getFactoryUInt32Type().newBuff();
		}
		else if( classCode.equals( "a86b" ) ) {
			buff = schema.getFactoryUInt32Col().newBuff();
		}
		else if( classCode.equals( "a84e" ) ) {
			buff = schema.getFactoryUInt64Def().newBuff();
		}
		else if( classCode.equals( "a84f" ) ) {
			buff = schema.getFactoryUInt64Type().newBuff();
		}
		else if( classCode.equals( "a86c" ) ) {
			buff = schema.getFactoryUInt64Col().newBuff();
		}
		else if( classCode.equals( "a850" ) ) {
			buff = schema.getFactoryUuidDef().newBuff();
		}
		else if( classCode.equals( "a851" ) ) {
			buff = schema.getFactoryUuidType().newBuff();
		}
		else if( classCode.equals( "a86e" ) ) {
			buff = schema.getFactoryUuidGen().newBuff();
		}
		else if( classCode.equals( "a86d" ) ) {
			buff = schema.getFactoryUuidCol().newBuff();
		}
		else {
			throw new CFLibRuntimeException( getClass(),
				S_ProcName,
				"Unrecognized class code \"" + classCode + "\"" );
		}
		{
			String colString = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setCreatedByUserId( null );
			}
			else if( ( colString == null ) || ( colString.length() <= 0 ) ) {
				buff.setCreatedByUserId( null );
			}
			else {
				buff.setCreatedByUserId( UUID.fromString( colString ) );
			}
			idxcol ++;

			colString = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setCreatedAt( null );
			}
			else if( ( colString == null ) || ( colString.length() <= 0 ) ) {
				buff.setCreatedAt( null );
			}
			else {
				buff.setCreatedAt( CFBamPgSqlSchema.convertTimestampString( colString ) );
			}
			idxcol++;
			colString = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setUpdatedByUserId( null );
			}
			else if( ( colString == null ) || ( colString.length() <= 0 ) ) {
				buff.setUpdatedByUserId( null );
			}
			else {
				buff.setUpdatedByUserId( UUID.fromString( colString ) );
			}
			idxcol ++;

			colString = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setUpdatedAt( null );
			}
			else if( ( colString == null ) || ( colString.length() <= 0 ) ) {
				buff.setUpdatedAt( null );
			}
			else {
				buff.setUpdatedAt( CFBamPgSqlSchema.convertTimestampString( colString ) );
			}
			idxcol++;
		}
		buff.setRequiredTenantId( resultSet.getLong( idxcol ) );
		idxcol++;
		buff.setRequiredScopeId( resultSet.getLong( idxcol ) );
		idxcol++;
		buff.setRequiredId( resultSet.getLong( idxcol ) );
		idxcol++;
		{
			long colVal = resultSet.getLong( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalDefSchemaTenantId( null );
			}
			else {
				buff.setOptionalDefSchemaTenantId( colVal );
			}
		}
		idxcol++;
		{
			long colVal = resultSet.getLong( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalDefSchemaId( null );
			}
			else {
				buff.setOptionalDefSchemaId( colVal );
			}
		}
		idxcol++;
		buff.setRequiredName( resultSet.getString( idxcol ) );
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalShortName( null );
			}
			else {
				buff.setOptionalShortName( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalLabel( null );
			}
			else {
				buff.setOptionalLabel( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalShortDescription( null );
			}
			else {
				buff.setOptionalShortDescription( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalDescription( null );
			}
			else {
				buff.setOptionalDescription( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalDefaultXmlValue( null );
			}
			else {
				buff.setOptionalDefaultXmlValue( colVal );
			}
		}
		idxcol++;
		buff.setRequiredIsNullable( resultSet.getBoolean( idxcol ) );
		idxcol++;
		{
			boolean colVal = resultSet.getBoolean( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalGenerateId( null );
			}
			else {
				buff.setOptionalGenerateId( colVal );
			}
		}
		idxcol++;
		buff.setRequiredImplementsPolymorph( resultSet.getBoolean( idxcol ) );
		idxcol++;
		{
			long colVal = resultSet.getLong( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalPrevTenantId( null );
			}
			else {
				buff.setOptionalPrevTenantId( colVal );
			}
		}
		idxcol++;
		{
			long colVal = resultSet.getLong( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalPrevId( null );
			}
			else {
				buff.setOptionalPrevId( colVal );
			}
		}
		idxcol++;
		{
			long colVal = resultSet.getLong( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalNextTenantId( null );
			}
			else {
				buff.setOptionalNextTenantId( colVal );
			}
		}
		idxcol++;
		{
			long colVal = resultSet.getLong( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalNextId( null );
			}
			else {
				buff.setOptionalNextId( colVal );
			}
		}
		idxcol++;
		{
			String colVal = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setOptionalDbName( null );
			}
			else {
				buff.setOptionalDbName( colVal );
			}
		}
		idxcol++;

		buff.setRequiredRevision( resultSet.getInt( idxcol ) );
		return( buff );
	}

	public CFBamAtomBuff readDerived( CFSecAuthorization Authorization,
		CFBamValuePKey PKey )
	{
		final String S_ProcName = "readDerived";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamAtomBuff buff;

		String classCode;
		ResultSet resultSet = null;
		try {
			long TenantId = PKey.getRequiredTenantId();
			long Id = PKey.getRequiredId();
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_cc_atomdef( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadDerivedClassCode == null ) {
				stmtReadDerivedClassCode = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadDerivedClassCode.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadDerivedClassCode.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadDerivedClassCode.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadDerivedClassCode.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadDerivedClassCode.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadDerivedClassCode.setLong( argIdx++, TenantId );
			stmtReadDerivedClassCode.setLong( argIdx++, Id );
			resultSet = stmtReadDerivedClassCode.executeQuery();
			if( resultSet.next() ) {
				classCode = resultSet.getString( 1 );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
			}
			else {
				return( null );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
		if( classCode.equals( "a80a" ) ) {
			buff = readBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a80b" ) ) {
			buff = schema.getTableBlobDef().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a80c" ) ) {
			buff = schema.getTableBlobType().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a852" ) ) {
			buff = schema.getTableBlobCol().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a80d" ) ) {
			buff = schema.getTableBoolDef().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a80e" ) ) {
			buff = schema.getTableBoolType().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a853" ) ) {
			buff = schema.getTableBoolCol().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a815" ) ) {
			buff = schema.getTableDateDef().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a816" ) ) {
			buff = schema.getTableDateType().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a854" ) ) {
			buff = schema.getTableDateCol().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a81c" ) ) {
			buff = schema.getTableDoubleDef().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a81d" ) ) {
			buff = schema.getTableDoubleType().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a855" ) ) {
			buff = schema.getTableDoubleCol().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a81f" ) ) {
			buff = schema.getTableFloatDef().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a820" ) ) {
			buff = schema.getTableFloatType().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a858" ) ) {
			buff = schema.getTableFloatCol().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a823" ) ) {
			buff = schema.getTableInt16Def().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a824" ) ) {
			buff = schema.getTableInt16Type().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a859" ) ) {
			buff = schema.getTableId16Gen().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a856" ) ) {
			buff = schema.getTableEnumDef().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a857" ) ) {
			buff = schema.getTableEnumType().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a85c" ) ) {
			buff = schema.getTableInt16Col().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a825" ) ) {
			buff = schema.getTableInt32Def().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a826" ) ) {
			buff = schema.getTableInt32Type().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a85a" ) ) {
			buff = schema.getTableId32Gen().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a85d" ) ) {
			buff = schema.getTableInt32Col().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a827" ) ) {
			buff = schema.getTableInt64Def().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a828" ) ) {
			buff = schema.getTableInt64Type().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a85b" ) ) {
			buff = schema.getTableId64Gen().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a85e" ) ) {
			buff = schema.getTableInt64Col().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a829" ) ) {
			buff = schema.getTableNmTokenDef().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a82a" ) ) {
			buff = schema.getTableNmTokenType().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a85f" ) ) {
			buff = schema.getTableNmTokenCol().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a82b" ) ) {
			buff = schema.getTableNmTokensDef().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a82c" ) ) {
			buff = schema.getTableNmTokensType().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a860" ) ) {
			buff = schema.getTableNmTokensCol().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a82d" ) ) {
			buff = schema.getTableNumberDef().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a82e" ) ) {
			buff = schema.getTableNumberType().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a861" ) ) {
			buff = schema.getTableNumberCol().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a838" ) ) {
			buff = schema.getTableStringDef().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a839" ) ) {
			buff = schema.getTableStringType().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a862" ) ) {
			buff = schema.getTableStringCol().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a83a" ) ) {
			buff = schema.getTableTZDateDef().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a83b" ) ) {
			buff = schema.getTableTZDateType().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a863" ) ) {
			buff = schema.getTableTZDateCol().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a83c" ) ) {
			buff = schema.getTableTZTimeDef().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a83d" ) ) {
			buff = schema.getTableTZTimeType().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a864" ) ) {
			buff = schema.getTableTZTimeCol().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a83e" ) ) {
			buff = schema.getTableTZTimestampDef().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a83f" ) ) {
			buff = schema.getTableTZTimestampType().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a865" ) ) {
			buff = schema.getTableTZTimestampCol().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a841" ) ) {
			buff = schema.getTableTextDef().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a842" ) ) {
			buff = schema.getTableTextType().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a866" ) ) {
			buff = schema.getTableTextCol().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a843" ) ) {
			buff = schema.getTableTimeDef().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a845" ) ) {
			buff = schema.getTableTimeType().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a867" ) ) {
			buff = schema.getTableTimeCol().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a846" ) ) {
			buff = schema.getTableTimestampDef().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a847" ) ) {
			buff = schema.getTableTimestampType().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a868" ) ) {
			buff = schema.getTableTimestampCol().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a848" ) ) {
			buff = schema.getTableTokenDef().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a849" ) ) {
			buff = schema.getTableTokenType().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a869" ) ) {
			buff = schema.getTableTokenCol().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a84a" ) ) {
			buff = schema.getTableUInt16Def().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a84b" ) ) {
			buff = schema.getTableUInt16Type().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a86a" ) ) {
			buff = schema.getTableUInt16Col().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a84c" ) ) {
			buff = schema.getTableUInt32Def().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a84d" ) ) {
			buff = schema.getTableUInt32Type().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a86b" ) ) {
			buff = schema.getTableUInt32Col().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a84e" ) ) {
			buff = schema.getTableUInt64Def().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a84f" ) ) {
			buff = schema.getTableUInt64Type().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a86c" ) ) {
			buff = schema.getTableUInt64Col().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a850" ) ) {
			buff = schema.getTableUuidDef().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a851" ) ) {
			buff = schema.getTableUuidType().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a86e" ) ) {
			buff = schema.getTableUuidGen().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else if( classCode.equals( "a86d" ) ) {
			buff = schema.getTableUuidCol().readBuffByIdIdx( Authorization,
				PKey.getRequiredTenantId(),
				PKey.getRequiredId() );
		}
		else {
			throw new CFLibRuntimeException( getClass(),
				S_ProcName,
				"Did not expect ClassCode \"" + classCode + "\"" );
		}
		return( buff );
	}

	public CFBamAtomBuff lockDerived( CFSecAuthorization Authorization,
		CFBamValuePKey PKey )
	{
		final String S_ProcName = "lockDerived";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFBamAtomBuff buff;
		String classCode;
		ResultSet resultSet = null;
		try {
			long TenantId = PKey.getRequiredTenantId();
			long Id = PKey.getRequiredId();
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_cc_atomdef( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtLockDerivedClassCode == null ) {
				stmtLockDerivedClassCode = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtLockDerivedClassCode.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtLockDerivedClassCode.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtLockDerivedClassCode.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtLockDerivedClassCode.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtLockDerivedClassCode.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtLockDerivedClassCode.setLong( argIdx++, TenantId );
			stmtLockDerivedClassCode.setLong( argIdx++, Id );
			resultSet = stmtLockDerivedClassCode.executeQuery();
			if( resultSet.next() ) {
				classCode = resultSet.getString( 1 );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
			}
			else {
				return( null );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
		if( classCode.equals( "a80a" ) ) {
			buff = lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a80b" ) ) {
			buff = schema.getTableBlobDef().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a80c" ) ) {
			buff = schema.getTableBlobType().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a852" ) ) {
			buff = schema.getTableBlobCol().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a80d" ) ) {
			buff = schema.getTableBoolDef().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a80e" ) ) {
			buff = schema.getTableBoolType().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a853" ) ) {
			buff = schema.getTableBoolCol().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a815" ) ) {
			buff = schema.getTableDateDef().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a816" ) ) {
			buff = schema.getTableDateType().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a854" ) ) {
			buff = schema.getTableDateCol().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a81c" ) ) {
			buff = schema.getTableDoubleDef().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a81d" ) ) {
			buff = schema.getTableDoubleType().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a855" ) ) {
			buff = schema.getTableDoubleCol().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a81f" ) ) {
			buff = schema.getTableFloatDef().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a820" ) ) {
			buff = schema.getTableFloatType().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a858" ) ) {
			buff = schema.getTableFloatCol().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a823" ) ) {
			buff = schema.getTableInt16Def().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a824" ) ) {
			buff = schema.getTableInt16Type().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a859" ) ) {
			buff = schema.getTableId16Gen().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a856" ) ) {
			buff = schema.getTableEnumDef().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a857" ) ) {
			buff = schema.getTableEnumType().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a85c" ) ) {
			buff = schema.getTableInt16Col().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a825" ) ) {
			buff = schema.getTableInt32Def().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a826" ) ) {
			buff = schema.getTableInt32Type().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a85a" ) ) {
			buff = schema.getTableId32Gen().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a85d" ) ) {
			buff = schema.getTableInt32Col().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a827" ) ) {
			buff = schema.getTableInt64Def().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a828" ) ) {
			buff = schema.getTableInt64Type().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a85b" ) ) {
			buff = schema.getTableId64Gen().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a85e" ) ) {
			buff = schema.getTableInt64Col().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a829" ) ) {
			buff = schema.getTableNmTokenDef().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a82a" ) ) {
			buff = schema.getTableNmTokenType().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a85f" ) ) {
			buff = schema.getTableNmTokenCol().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a82b" ) ) {
			buff = schema.getTableNmTokensDef().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a82c" ) ) {
			buff = schema.getTableNmTokensType().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a860" ) ) {
			buff = schema.getTableNmTokensCol().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a82d" ) ) {
			buff = schema.getTableNumberDef().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a82e" ) ) {
			buff = schema.getTableNumberType().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a861" ) ) {
			buff = schema.getTableNumberCol().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a838" ) ) {
			buff = schema.getTableStringDef().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a839" ) ) {
			buff = schema.getTableStringType().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a862" ) ) {
			buff = schema.getTableStringCol().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a83a" ) ) {
			buff = schema.getTableTZDateDef().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a83b" ) ) {
			buff = schema.getTableTZDateType().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a863" ) ) {
			buff = schema.getTableTZDateCol().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a83c" ) ) {
			buff = schema.getTableTZTimeDef().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a83d" ) ) {
			buff = schema.getTableTZTimeType().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a864" ) ) {
			buff = schema.getTableTZTimeCol().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a83e" ) ) {
			buff = schema.getTableTZTimestampDef().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a83f" ) ) {
			buff = schema.getTableTZTimestampType().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a865" ) ) {
			buff = schema.getTableTZTimestampCol().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a841" ) ) {
			buff = schema.getTableTextDef().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a842" ) ) {
			buff = schema.getTableTextType().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a866" ) ) {
			buff = schema.getTableTextCol().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a843" ) ) {
			buff = schema.getTableTimeDef().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a845" ) ) {
			buff = schema.getTableTimeType().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a867" ) ) {
			buff = schema.getTableTimeCol().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a846" ) ) {
			buff = schema.getTableTimestampDef().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a847" ) ) {
			buff = schema.getTableTimestampType().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a868" ) ) {
			buff = schema.getTableTimestampCol().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a848" ) ) {
			buff = schema.getTableTokenDef().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a849" ) ) {
			buff = schema.getTableTokenType().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a869" ) ) {
			buff = schema.getTableTokenCol().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a84a" ) ) {
			buff = schema.getTableUInt16Def().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a84b" ) ) {
			buff = schema.getTableUInt16Type().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a86a" ) ) {
			buff = schema.getTableUInt16Col().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a84c" ) ) {
			buff = schema.getTableUInt32Def().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a84d" ) ) {
			buff = schema.getTableUInt32Type().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a86b" ) ) {
			buff = schema.getTableUInt32Col().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a84e" ) ) {
			buff = schema.getTableUInt64Def().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a84f" ) ) {
			buff = schema.getTableUInt64Type().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a86c" ) ) {
			buff = schema.getTableUInt64Col().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a850" ) ) {
			buff = schema.getTableUuidDef().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a851" ) ) {
			buff = schema.getTableUuidType().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a86e" ) ) {
			buff = schema.getTableUuidGen().lockBuff( Authorization, PKey );
		}
		else if( classCode.equals( "a86d" ) ) {
			buff = schema.getTableUuidCol().lockBuff( Authorization, PKey );
		}
		else {
			throw new CFLibRuntimeException( getClass(),
				S_ProcName,
				"Did not expect ClassCode \"" + classCode + "\"" );
		}
		return( buff );
	}

	public CFBamAtomBuff[] readAllDerived( CFSecAuthorization Authorization ) {
		final String S_ProcName = "readAllDerived";
		CFBamAtomBuff[] buffArray;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		String classCode;
		ArrayList<String> classCodeList = new ArrayList<String>();
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_atomdef_cc_all( ?, ?, ?, ?, ? )";
			if( stmtReadAllClassCode == null ) {
				stmtReadAllClassCode = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadAllClassCode.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadAllClassCode.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadAllClassCode.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadAllClassCode.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadAllClassCode.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			resultSet = stmtReadAllClassCode.executeQuery();
			while( resultSet.next() ) {
				classCode = resultSet.getString( 1 );
				classCodeList.add( classCode );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
		List<CFBamAtomBuff> resultList = new LinkedList<CFBamAtomBuff>();
		for( int classCodeIdx = 0; classCodeIdx < classCodeList.size(); classCodeIdx ++ ) {
			CFBamAtomBuff[] subList;
			classCode = classCodeList.get( classCodeIdx );
			if( classCode.equals( "a80a" ) ) {
				subList = readAllBuff( Authorization );
			}
			else if( classCode.equals( "a80b" ) ) {
				subList = schema.getTableBlobDef().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a80c" ) ) {
				subList = schema.getTableBlobType().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a852" ) ) {
				subList = schema.getTableBlobCol().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a80d" ) ) {
				subList = schema.getTableBoolDef().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a80e" ) ) {
				subList = schema.getTableBoolType().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a853" ) ) {
				subList = schema.getTableBoolCol().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a815" ) ) {
				subList = schema.getTableDateDef().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a816" ) ) {
				subList = schema.getTableDateType().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a854" ) ) {
				subList = schema.getTableDateCol().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a81c" ) ) {
				subList = schema.getTableDoubleDef().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a81d" ) ) {
				subList = schema.getTableDoubleType().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a855" ) ) {
				subList = schema.getTableDoubleCol().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a81f" ) ) {
				subList = schema.getTableFloatDef().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a820" ) ) {
				subList = schema.getTableFloatType().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a858" ) ) {
				subList = schema.getTableFloatCol().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a823" ) ) {
				subList = schema.getTableInt16Def().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a824" ) ) {
				subList = schema.getTableInt16Type().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a859" ) ) {
				subList = schema.getTableId16Gen().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a856" ) ) {
				subList = schema.getTableEnumDef().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a857" ) ) {
				subList = schema.getTableEnumType().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a85c" ) ) {
				subList = schema.getTableInt16Col().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a825" ) ) {
				subList = schema.getTableInt32Def().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a826" ) ) {
				subList = schema.getTableInt32Type().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a85a" ) ) {
				subList = schema.getTableId32Gen().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a85d" ) ) {
				subList = schema.getTableInt32Col().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a827" ) ) {
				subList = schema.getTableInt64Def().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a828" ) ) {
				subList = schema.getTableInt64Type().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a85b" ) ) {
				subList = schema.getTableId64Gen().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a85e" ) ) {
				subList = schema.getTableInt64Col().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a829" ) ) {
				subList = schema.getTableNmTokenDef().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a82a" ) ) {
				subList = schema.getTableNmTokenType().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a85f" ) ) {
				subList = schema.getTableNmTokenCol().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a82b" ) ) {
				subList = schema.getTableNmTokensDef().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a82c" ) ) {
				subList = schema.getTableNmTokensType().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a860" ) ) {
				subList = schema.getTableNmTokensCol().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a82d" ) ) {
				subList = schema.getTableNumberDef().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a82e" ) ) {
				subList = schema.getTableNumberType().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a861" ) ) {
				subList = schema.getTableNumberCol().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a838" ) ) {
				subList = schema.getTableStringDef().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a839" ) ) {
				subList = schema.getTableStringType().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a862" ) ) {
				subList = schema.getTableStringCol().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a83a" ) ) {
				subList = schema.getTableTZDateDef().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a83b" ) ) {
				subList = schema.getTableTZDateType().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a863" ) ) {
				subList = schema.getTableTZDateCol().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a83c" ) ) {
				subList = schema.getTableTZTimeDef().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a83d" ) ) {
				subList = schema.getTableTZTimeType().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a864" ) ) {
				subList = schema.getTableTZTimeCol().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a83e" ) ) {
				subList = schema.getTableTZTimestampDef().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a83f" ) ) {
				subList = schema.getTableTZTimestampType().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a865" ) ) {
				subList = schema.getTableTZTimestampCol().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a841" ) ) {
				subList = schema.getTableTextDef().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a842" ) ) {
				subList = schema.getTableTextType().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a866" ) ) {
				subList = schema.getTableTextCol().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a843" ) ) {
				subList = schema.getTableTimeDef().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a845" ) ) {
				subList = schema.getTableTimeType().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a867" ) ) {
				subList = schema.getTableTimeCol().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a846" ) ) {
				subList = schema.getTableTimestampDef().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a847" ) ) {
				subList = schema.getTableTimestampType().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a868" ) ) {
				subList = schema.getTableTimestampCol().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a848" ) ) {
				subList = schema.getTableTokenDef().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a849" ) ) {
				subList = schema.getTableTokenType().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a869" ) ) {
				subList = schema.getTableTokenCol().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a84a" ) ) {
				subList = schema.getTableUInt16Def().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a84b" ) ) {
				subList = schema.getTableUInt16Type().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a86a" ) ) {
				subList = schema.getTableUInt16Col().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a84c" ) ) {
				subList = schema.getTableUInt32Def().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a84d" ) ) {
				subList = schema.getTableUInt32Type().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a86b" ) ) {
				subList = schema.getTableUInt32Col().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a84e" ) ) {
				subList = schema.getTableUInt64Def().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a84f" ) ) {
				subList = schema.getTableUInt64Type().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a86c" ) ) {
				subList = schema.getTableUInt64Col().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a850" ) ) {
				subList = schema.getTableUuidDef().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a851" ) ) {
				subList = schema.getTableUuidType().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a86e" ) ) {
				subList = schema.getTableUuidGen().readAllBuff( Authorization );
			}
			else if( classCode.equals( "a86d" ) ) {
				subList = schema.getTableUuidCol().readAllBuff( Authorization );
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Did not expect ClassCode \"" + classCode + "\"" );
			}
			for( int idxSubList = 0; idxSubList < subList.length; idxSubList ++ ) {
				resultList.add( subList[ idxSubList ] );
			}
		}
		int idx = 0;
		buffArray = new CFBamAtomBuff[ resultList.size() ];
		Iterator<CFBamAtomBuff> iter = resultList.iterator();
		while( iter.hasNext() ) {
			buffArray[idx++] = iter.next();
		}
		return( buffArray );
	}

	public CFBamAtomBuff readDerivedByIdIdx( CFSecAuthorization Authorization,
		long TenantId,
		long Id )
	{
		final String S_ProcName = "CFBamPgSqlAtomTable.readDerivedByIdIdx() ";
		CFBamAtomBuff buff;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		String classCode;
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_atomdef_cc_by_ididx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadClassCodeByIdIdx == null ) {
				stmtReadClassCodeByIdIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadClassCodeByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadClassCodeByIdIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadClassCodeByIdIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadClassCodeByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadClassCodeByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadClassCodeByIdIdx.setLong( argIdx++, TenantId );
			stmtReadClassCodeByIdIdx.setLong( argIdx++, Id );
			resultSet = stmtReadClassCodeByIdIdx.executeQuery();
			if( resultSet.next() ) {
				classCode = resultSet.getString( 1 );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
			}
			else {
				return( null );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
		if( classCode.equals( "a80a" ) ) {
			buff = readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a80b" ) ) {
			buff = schema.getTableBlobDef().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a80c" ) ) {
			buff = schema.getTableBlobType().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a852" ) ) {
			buff = schema.getTableBlobCol().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a80d" ) ) {
			buff = schema.getTableBoolDef().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a80e" ) ) {
			buff = schema.getTableBoolType().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a853" ) ) {
			buff = schema.getTableBoolCol().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a815" ) ) {
			buff = schema.getTableDateDef().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a816" ) ) {
			buff = schema.getTableDateType().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a854" ) ) {
			buff = schema.getTableDateCol().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a81c" ) ) {
			buff = schema.getTableDoubleDef().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a81d" ) ) {
			buff = schema.getTableDoubleType().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a855" ) ) {
			buff = schema.getTableDoubleCol().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a81f" ) ) {
			buff = schema.getTableFloatDef().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a820" ) ) {
			buff = schema.getTableFloatType().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a858" ) ) {
			buff = schema.getTableFloatCol().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a823" ) ) {
			buff = schema.getTableInt16Def().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a824" ) ) {
			buff = schema.getTableInt16Type().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a859" ) ) {
			buff = schema.getTableId16Gen().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a856" ) ) {
			buff = schema.getTableEnumDef().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a857" ) ) {
			buff = schema.getTableEnumType().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a85c" ) ) {
			buff = schema.getTableInt16Col().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a825" ) ) {
			buff = schema.getTableInt32Def().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a826" ) ) {
			buff = schema.getTableInt32Type().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a85a" ) ) {
			buff = schema.getTableId32Gen().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a85d" ) ) {
			buff = schema.getTableInt32Col().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a827" ) ) {
			buff = schema.getTableInt64Def().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a828" ) ) {
			buff = schema.getTableInt64Type().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a85b" ) ) {
			buff = schema.getTableId64Gen().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a85e" ) ) {
			buff = schema.getTableInt64Col().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a829" ) ) {
			buff = schema.getTableNmTokenDef().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a82a" ) ) {
			buff = schema.getTableNmTokenType().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a85f" ) ) {
			buff = schema.getTableNmTokenCol().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a82b" ) ) {
			buff = schema.getTableNmTokensDef().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a82c" ) ) {
			buff = schema.getTableNmTokensType().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a860" ) ) {
			buff = schema.getTableNmTokensCol().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a82d" ) ) {
			buff = schema.getTableNumberDef().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a82e" ) ) {
			buff = schema.getTableNumberType().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a861" ) ) {
			buff = schema.getTableNumberCol().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a838" ) ) {
			buff = schema.getTableStringDef().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a839" ) ) {
			buff = schema.getTableStringType().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a862" ) ) {
			buff = schema.getTableStringCol().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a83a" ) ) {
			buff = schema.getTableTZDateDef().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a83b" ) ) {
			buff = schema.getTableTZDateType().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a863" ) ) {
			buff = schema.getTableTZDateCol().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a83c" ) ) {
			buff = schema.getTableTZTimeDef().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a83d" ) ) {
			buff = schema.getTableTZTimeType().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a864" ) ) {
			buff = schema.getTableTZTimeCol().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a83e" ) ) {
			buff = schema.getTableTZTimestampDef().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a83f" ) ) {
			buff = schema.getTableTZTimestampType().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a865" ) ) {
			buff = schema.getTableTZTimestampCol().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a841" ) ) {
			buff = schema.getTableTextDef().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a842" ) ) {
			buff = schema.getTableTextType().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a866" ) ) {
			buff = schema.getTableTextCol().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a843" ) ) {
			buff = schema.getTableTimeDef().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a845" ) ) {
			buff = schema.getTableTimeType().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a867" ) ) {
			buff = schema.getTableTimeCol().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a846" ) ) {
			buff = schema.getTableTimestampDef().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a847" ) ) {
			buff = schema.getTableTimestampType().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a868" ) ) {
			buff = schema.getTableTimestampCol().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a848" ) ) {
			buff = schema.getTableTokenDef().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a849" ) ) {
			buff = schema.getTableTokenType().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a869" ) ) {
			buff = schema.getTableTokenCol().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a84a" ) ) {
			buff = schema.getTableUInt16Def().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a84b" ) ) {
			buff = schema.getTableUInt16Type().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a86a" ) ) {
			buff = schema.getTableUInt16Col().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a84c" ) ) {
			buff = schema.getTableUInt32Def().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a84d" ) ) {
			buff = schema.getTableUInt32Type().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a86b" ) ) {
			buff = schema.getTableUInt32Col().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a84e" ) ) {
			buff = schema.getTableUInt64Def().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a84f" ) ) {
			buff = schema.getTableUInt64Type().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a86c" ) ) {
			buff = schema.getTableUInt64Col().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a850" ) ) {
			buff = schema.getTableUuidDef().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a851" ) ) {
			buff = schema.getTableUuidType().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a86e" ) ) {
			buff = schema.getTableUuidGen().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else if( classCode.equals( "a86d" ) ) {
			buff = schema.getTableUuidCol().readBuffByIdIdx( Authorization,
				TenantId,
				Id );
		}
		else {
			throw new CFLibRuntimeException( getClass(),
				S_ProcName,
				"Did not expect ClassCode \"" + classCode + "\"" );
		}
		return( buff );
	}

	public CFBamAtomBuff readDerivedByUNameIdx( CFSecAuthorization Authorization,
		long TenantId,
		long ScopeId,
		String Name )
	{
		final String S_ProcName = "CFBamPgSqlAtomTable.readDerivedByUNameIdx() ";
		CFBamAtomBuff buff;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		String classCode;
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_atomdef_cc_by_unameidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadClassCodeByUNameIdx == null ) {
				stmtReadClassCodeByUNameIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadClassCodeByUNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadClassCodeByUNameIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadClassCodeByUNameIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadClassCodeByUNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadClassCodeByUNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadClassCodeByUNameIdx.setLong( argIdx++, TenantId );
			stmtReadClassCodeByUNameIdx.setLong( argIdx++, ScopeId );
			stmtReadClassCodeByUNameIdx.setString( argIdx++, Name );
			resultSet = stmtReadClassCodeByUNameIdx.executeQuery();
			if( resultSet.next() ) {
				classCode = resultSet.getString( 1 );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
			}
			else {
				return( null );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
		if( classCode.equals( "a80a" ) ) {
			buff = readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a80b" ) ) {
			buff = schema.getTableBlobDef().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a80c" ) ) {
			buff = schema.getTableBlobType().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a852" ) ) {
			buff = schema.getTableBlobCol().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a80d" ) ) {
			buff = schema.getTableBoolDef().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a80e" ) ) {
			buff = schema.getTableBoolType().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a853" ) ) {
			buff = schema.getTableBoolCol().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a815" ) ) {
			buff = schema.getTableDateDef().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a816" ) ) {
			buff = schema.getTableDateType().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a854" ) ) {
			buff = schema.getTableDateCol().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a81c" ) ) {
			buff = schema.getTableDoubleDef().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a81d" ) ) {
			buff = schema.getTableDoubleType().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a855" ) ) {
			buff = schema.getTableDoubleCol().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a81f" ) ) {
			buff = schema.getTableFloatDef().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a820" ) ) {
			buff = schema.getTableFloatType().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a858" ) ) {
			buff = schema.getTableFloatCol().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a823" ) ) {
			buff = schema.getTableInt16Def().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a824" ) ) {
			buff = schema.getTableInt16Type().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a859" ) ) {
			buff = schema.getTableId16Gen().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a856" ) ) {
			buff = schema.getTableEnumDef().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a857" ) ) {
			buff = schema.getTableEnumType().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a85c" ) ) {
			buff = schema.getTableInt16Col().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a825" ) ) {
			buff = schema.getTableInt32Def().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a826" ) ) {
			buff = schema.getTableInt32Type().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a85a" ) ) {
			buff = schema.getTableId32Gen().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a85d" ) ) {
			buff = schema.getTableInt32Col().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a827" ) ) {
			buff = schema.getTableInt64Def().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a828" ) ) {
			buff = schema.getTableInt64Type().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a85b" ) ) {
			buff = schema.getTableId64Gen().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a85e" ) ) {
			buff = schema.getTableInt64Col().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a829" ) ) {
			buff = schema.getTableNmTokenDef().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a82a" ) ) {
			buff = schema.getTableNmTokenType().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a85f" ) ) {
			buff = schema.getTableNmTokenCol().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a82b" ) ) {
			buff = schema.getTableNmTokensDef().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a82c" ) ) {
			buff = schema.getTableNmTokensType().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a860" ) ) {
			buff = schema.getTableNmTokensCol().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a82d" ) ) {
			buff = schema.getTableNumberDef().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a82e" ) ) {
			buff = schema.getTableNumberType().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a861" ) ) {
			buff = schema.getTableNumberCol().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a838" ) ) {
			buff = schema.getTableStringDef().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a839" ) ) {
			buff = schema.getTableStringType().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a862" ) ) {
			buff = schema.getTableStringCol().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a83a" ) ) {
			buff = schema.getTableTZDateDef().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a83b" ) ) {
			buff = schema.getTableTZDateType().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a863" ) ) {
			buff = schema.getTableTZDateCol().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a83c" ) ) {
			buff = schema.getTableTZTimeDef().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a83d" ) ) {
			buff = schema.getTableTZTimeType().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a864" ) ) {
			buff = schema.getTableTZTimeCol().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a83e" ) ) {
			buff = schema.getTableTZTimestampDef().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a83f" ) ) {
			buff = schema.getTableTZTimestampType().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a865" ) ) {
			buff = schema.getTableTZTimestampCol().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a841" ) ) {
			buff = schema.getTableTextDef().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a842" ) ) {
			buff = schema.getTableTextType().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a866" ) ) {
			buff = schema.getTableTextCol().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a843" ) ) {
			buff = schema.getTableTimeDef().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a845" ) ) {
			buff = schema.getTableTimeType().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a867" ) ) {
			buff = schema.getTableTimeCol().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a846" ) ) {
			buff = schema.getTableTimestampDef().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a847" ) ) {
			buff = schema.getTableTimestampType().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a868" ) ) {
			buff = schema.getTableTimestampCol().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a848" ) ) {
			buff = schema.getTableTokenDef().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a849" ) ) {
			buff = schema.getTableTokenType().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a869" ) ) {
			buff = schema.getTableTokenCol().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a84a" ) ) {
			buff = schema.getTableUInt16Def().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a84b" ) ) {
			buff = schema.getTableUInt16Type().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a86a" ) ) {
			buff = schema.getTableUInt16Col().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a84c" ) ) {
			buff = schema.getTableUInt32Def().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a84d" ) ) {
			buff = schema.getTableUInt32Type().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a86b" ) ) {
			buff = schema.getTableUInt32Col().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a84e" ) ) {
			buff = schema.getTableUInt64Def().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a84f" ) ) {
			buff = schema.getTableUInt64Type().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a86c" ) ) {
			buff = schema.getTableUInt64Col().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a850" ) ) {
			buff = schema.getTableUuidDef().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a851" ) ) {
			buff = schema.getTableUuidType().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a86e" ) ) {
			buff = schema.getTableUuidGen().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else if( classCode.equals( "a86d" ) ) {
			buff = schema.getTableUuidCol().readBuffByUNameIdx( Authorization,
				TenantId,
				ScopeId,
				Name );
		}
		else {
			throw new CFLibRuntimeException( getClass(),
				S_ProcName,
				"Did not expect ClassCode \"" + classCode + "\"" );
		}
		return( buff );
	}

	public CFBamAtomBuff[] readDerivedByValTentIdx( CFSecAuthorization Authorization,
		long TenantId )
	{
		final String S_ProcName = "readDerivedByValTentIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ArrayList<String> classCodeList = new ArrayList<String>();
		String classCode;
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_atomdef_cc_by_valtentidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + " )";
			if( stmtReadClassCodeByValTentIdx == null ) {
				stmtReadClassCodeByValTentIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadClassCodeByValTentIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadClassCodeByValTentIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadClassCodeByValTentIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadClassCodeByValTentIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadClassCodeByValTentIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadClassCodeByValTentIdx.setLong( argIdx++, TenantId );
			resultSet = stmtReadClassCodeByValTentIdx.executeQuery();
			while( resultSet.next() ) {
				classCode = resultSet.getString( 1 );
				classCodeList.add( classCode );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
		List<CFBamAtomBuff> resultList = new LinkedList<CFBamAtomBuff>();
		ListIterator<String> classCodeIter = classCodeList.listIterator();
		while( classCodeIter.hasNext() ) {
			classCode = classCodeIter.next();
			if( classCode.equals( "a80a" ) ) {
				CFBamAtomBuff[] subList
					= readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a80b" ) ) {
				CFBamBlobDefBuff[] subList
					= schema.getTableBlobDef().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a80c" ) ) {
				CFBamBlobTypeBuff[] subList
					= schema.getTableBlobType().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a852" ) ) {
				CFBamBlobColBuff[] subList
					= schema.getTableBlobCol().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a80d" ) ) {
				CFBamBoolDefBuff[] subList
					= schema.getTableBoolDef().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a80e" ) ) {
				CFBamBoolTypeBuff[] subList
					= schema.getTableBoolType().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a853" ) ) {
				CFBamBoolColBuff[] subList
					= schema.getTableBoolCol().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a815" ) ) {
				CFBamDateDefBuff[] subList
					= schema.getTableDateDef().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a816" ) ) {
				CFBamDateTypeBuff[] subList
					= schema.getTableDateType().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a854" ) ) {
				CFBamDateColBuff[] subList
					= schema.getTableDateCol().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a81c" ) ) {
				CFBamDoubleDefBuff[] subList
					= schema.getTableDoubleDef().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a81d" ) ) {
				CFBamDoubleTypeBuff[] subList
					= schema.getTableDoubleType().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a855" ) ) {
				CFBamDoubleColBuff[] subList
					= schema.getTableDoubleCol().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a81f" ) ) {
				CFBamFloatDefBuff[] subList
					= schema.getTableFloatDef().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a820" ) ) {
				CFBamFloatTypeBuff[] subList
					= schema.getTableFloatType().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a858" ) ) {
				CFBamFloatColBuff[] subList
					= schema.getTableFloatCol().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a823" ) ) {
				CFBamInt16DefBuff[] subList
					= schema.getTableInt16Def().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a824" ) ) {
				CFBamInt16TypeBuff[] subList
					= schema.getTableInt16Type().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a859" ) ) {
				CFBamId16GenBuff[] subList
					= schema.getTableId16Gen().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a856" ) ) {
				CFBamEnumDefBuff[] subList
					= schema.getTableEnumDef().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a857" ) ) {
				CFBamEnumTypeBuff[] subList
					= schema.getTableEnumType().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85c" ) ) {
				CFBamInt16ColBuff[] subList
					= schema.getTableInt16Col().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a825" ) ) {
				CFBamInt32DefBuff[] subList
					= schema.getTableInt32Def().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a826" ) ) {
				CFBamInt32TypeBuff[] subList
					= schema.getTableInt32Type().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85a" ) ) {
				CFBamId32GenBuff[] subList
					= schema.getTableId32Gen().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85d" ) ) {
				CFBamInt32ColBuff[] subList
					= schema.getTableInt32Col().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a827" ) ) {
				CFBamInt64DefBuff[] subList
					= schema.getTableInt64Def().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a828" ) ) {
				CFBamInt64TypeBuff[] subList
					= schema.getTableInt64Type().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85b" ) ) {
				CFBamId64GenBuff[] subList
					= schema.getTableId64Gen().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85e" ) ) {
				CFBamInt64ColBuff[] subList
					= schema.getTableInt64Col().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a829" ) ) {
				CFBamNmTokenDefBuff[] subList
					= schema.getTableNmTokenDef().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a82a" ) ) {
				CFBamNmTokenTypeBuff[] subList
					= schema.getTableNmTokenType().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85f" ) ) {
				CFBamNmTokenColBuff[] subList
					= schema.getTableNmTokenCol().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a82b" ) ) {
				CFBamNmTokensDefBuff[] subList
					= schema.getTableNmTokensDef().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a82c" ) ) {
				CFBamNmTokensTypeBuff[] subList
					= schema.getTableNmTokensType().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a860" ) ) {
				CFBamNmTokensColBuff[] subList
					= schema.getTableNmTokensCol().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a82d" ) ) {
				CFBamNumberDefBuff[] subList
					= schema.getTableNumberDef().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a82e" ) ) {
				CFBamNumberTypeBuff[] subList
					= schema.getTableNumberType().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a861" ) ) {
				CFBamNumberColBuff[] subList
					= schema.getTableNumberCol().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a838" ) ) {
				CFBamStringDefBuff[] subList
					= schema.getTableStringDef().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a839" ) ) {
				CFBamStringTypeBuff[] subList
					= schema.getTableStringType().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a862" ) ) {
				CFBamStringColBuff[] subList
					= schema.getTableStringCol().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83a" ) ) {
				CFBamTZDateDefBuff[] subList
					= schema.getTableTZDateDef().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83b" ) ) {
				CFBamTZDateTypeBuff[] subList
					= schema.getTableTZDateType().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a863" ) ) {
				CFBamTZDateColBuff[] subList
					= schema.getTableTZDateCol().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83c" ) ) {
				CFBamTZTimeDefBuff[] subList
					= schema.getTableTZTimeDef().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83d" ) ) {
				CFBamTZTimeTypeBuff[] subList
					= schema.getTableTZTimeType().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a864" ) ) {
				CFBamTZTimeColBuff[] subList
					= schema.getTableTZTimeCol().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83e" ) ) {
				CFBamTZTimestampDefBuff[] subList
					= schema.getTableTZTimestampDef().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83f" ) ) {
				CFBamTZTimestampTypeBuff[] subList
					= schema.getTableTZTimestampType().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a865" ) ) {
				CFBamTZTimestampColBuff[] subList
					= schema.getTableTZTimestampCol().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a841" ) ) {
				CFBamTextDefBuff[] subList
					= schema.getTableTextDef().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a842" ) ) {
				CFBamTextTypeBuff[] subList
					= schema.getTableTextType().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a866" ) ) {
				CFBamTextColBuff[] subList
					= schema.getTableTextCol().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a843" ) ) {
				CFBamTimeDefBuff[] subList
					= schema.getTableTimeDef().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a845" ) ) {
				CFBamTimeTypeBuff[] subList
					= schema.getTableTimeType().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a867" ) ) {
				CFBamTimeColBuff[] subList
					= schema.getTableTimeCol().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a846" ) ) {
				CFBamTimestampDefBuff[] subList
					= schema.getTableTimestampDef().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a847" ) ) {
				CFBamTimestampTypeBuff[] subList
					= schema.getTableTimestampType().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a868" ) ) {
				CFBamTimestampColBuff[] subList
					= schema.getTableTimestampCol().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a848" ) ) {
				CFBamTokenDefBuff[] subList
					= schema.getTableTokenDef().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a849" ) ) {
				CFBamTokenTypeBuff[] subList
					= schema.getTableTokenType().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a869" ) ) {
				CFBamTokenColBuff[] subList
					= schema.getTableTokenCol().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84a" ) ) {
				CFBamUInt16DefBuff[] subList
					= schema.getTableUInt16Def().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84b" ) ) {
				CFBamUInt16TypeBuff[] subList
					= schema.getTableUInt16Type().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a86a" ) ) {
				CFBamUInt16ColBuff[] subList
					= schema.getTableUInt16Col().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84c" ) ) {
				CFBamUInt32DefBuff[] subList
					= schema.getTableUInt32Def().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84d" ) ) {
				CFBamUInt32TypeBuff[] subList
					= schema.getTableUInt32Type().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a86b" ) ) {
				CFBamUInt32ColBuff[] subList
					= schema.getTableUInt32Col().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84e" ) ) {
				CFBamUInt64DefBuff[] subList
					= schema.getTableUInt64Def().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84f" ) ) {
				CFBamUInt64TypeBuff[] subList
					= schema.getTableUInt64Type().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a86c" ) ) {
				CFBamUInt64ColBuff[] subList
					= schema.getTableUInt64Col().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a850" ) ) {
				CFBamUuidDefBuff[] subList
					= schema.getTableUuidDef().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a851" ) ) {
				CFBamUuidTypeBuff[] subList
					= schema.getTableUuidType().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a86e" ) ) {
				CFBamUuidGenBuff[] subList
					= schema.getTableUuidGen().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a86d" ) ) {
				CFBamUuidColBuff[] subList
					= schema.getTableUuidCol().readBuffByValTentIdx( Authorization,
						TenantId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Did not expect ClassCode \"" + classCode + "\"" );
			}
		}
		int idx = 0;
		CFBamAtomBuff[] retBuff = new CFBamAtomBuff[ resultList.size() ];
		Iterator<CFBamAtomBuff> iter = resultList.iterator();
		while( iter.hasNext() ) {
			retBuff[idx++] = iter.next();
		}
		return( retBuff );

	}

	public CFBamAtomBuff[] readDerivedByScopeIdx( CFSecAuthorization Authorization,
		long TenantId,
		long ScopeId )
	{
		final String S_ProcName = "readDerivedByScopeIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ArrayList<String> classCodeList = new ArrayList<String>();
		String classCode;
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_atomdef_cc_by_scopeidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadClassCodeByScopeIdx == null ) {
				stmtReadClassCodeByScopeIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadClassCodeByScopeIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadClassCodeByScopeIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadClassCodeByScopeIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadClassCodeByScopeIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadClassCodeByScopeIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadClassCodeByScopeIdx.setLong( argIdx++, TenantId );
			stmtReadClassCodeByScopeIdx.setLong( argIdx++, ScopeId );
			resultSet = stmtReadClassCodeByScopeIdx.executeQuery();
			while( resultSet.next() ) {
				classCode = resultSet.getString( 1 );
				classCodeList.add( classCode );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
		List<CFBamAtomBuff> resultList = new LinkedList<CFBamAtomBuff>();
		ListIterator<String> classCodeIter = classCodeList.listIterator();
		while( classCodeIter.hasNext() ) {
			classCode = classCodeIter.next();
			if( classCode.equals( "a80a" ) ) {
				CFBamAtomBuff[] subList
					= readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a80b" ) ) {
				CFBamBlobDefBuff[] subList
					= schema.getTableBlobDef().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a80c" ) ) {
				CFBamBlobTypeBuff[] subList
					= schema.getTableBlobType().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a852" ) ) {
				CFBamBlobColBuff[] subList
					= schema.getTableBlobCol().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a80d" ) ) {
				CFBamBoolDefBuff[] subList
					= schema.getTableBoolDef().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a80e" ) ) {
				CFBamBoolTypeBuff[] subList
					= schema.getTableBoolType().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a853" ) ) {
				CFBamBoolColBuff[] subList
					= schema.getTableBoolCol().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a815" ) ) {
				CFBamDateDefBuff[] subList
					= schema.getTableDateDef().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a816" ) ) {
				CFBamDateTypeBuff[] subList
					= schema.getTableDateType().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a854" ) ) {
				CFBamDateColBuff[] subList
					= schema.getTableDateCol().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a81c" ) ) {
				CFBamDoubleDefBuff[] subList
					= schema.getTableDoubleDef().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a81d" ) ) {
				CFBamDoubleTypeBuff[] subList
					= schema.getTableDoubleType().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a855" ) ) {
				CFBamDoubleColBuff[] subList
					= schema.getTableDoubleCol().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a81f" ) ) {
				CFBamFloatDefBuff[] subList
					= schema.getTableFloatDef().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a820" ) ) {
				CFBamFloatTypeBuff[] subList
					= schema.getTableFloatType().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a858" ) ) {
				CFBamFloatColBuff[] subList
					= schema.getTableFloatCol().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a823" ) ) {
				CFBamInt16DefBuff[] subList
					= schema.getTableInt16Def().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a824" ) ) {
				CFBamInt16TypeBuff[] subList
					= schema.getTableInt16Type().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a859" ) ) {
				CFBamId16GenBuff[] subList
					= schema.getTableId16Gen().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a856" ) ) {
				CFBamEnumDefBuff[] subList
					= schema.getTableEnumDef().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a857" ) ) {
				CFBamEnumTypeBuff[] subList
					= schema.getTableEnumType().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85c" ) ) {
				CFBamInt16ColBuff[] subList
					= schema.getTableInt16Col().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a825" ) ) {
				CFBamInt32DefBuff[] subList
					= schema.getTableInt32Def().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a826" ) ) {
				CFBamInt32TypeBuff[] subList
					= schema.getTableInt32Type().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85a" ) ) {
				CFBamId32GenBuff[] subList
					= schema.getTableId32Gen().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85d" ) ) {
				CFBamInt32ColBuff[] subList
					= schema.getTableInt32Col().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a827" ) ) {
				CFBamInt64DefBuff[] subList
					= schema.getTableInt64Def().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a828" ) ) {
				CFBamInt64TypeBuff[] subList
					= schema.getTableInt64Type().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85b" ) ) {
				CFBamId64GenBuff[] subList
					= schema.getTableId64Gen().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85e" ) ) {
				CFBamInt64ColBuff[] subList
					= schema.getTableInt64Col().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a829" ) ) {
				CFBamNmTokenDefBuff[] subList
					= schema.getTableNmTokenDef().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a82a" ) ) {
				CFBamNmTokenTypeBuff[] subList
					= schema.getTableNmTokenType().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85f" ) ) {
				CFBamNmTokenColBuff[] subList
					= schema.getTableNmTokenCol().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a82b" ) ) {
				CFBamNmTokensDefBuff[] subList
					= schema.getTableNmTokensDef().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a82c" ) ) {
				CFBamNmTokensTypeBuff[] subList
					= schema.getTableNmTokensType().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a860" ) ) {
				CFBamNmTokensColBuff[] subList
					= schema.getTableNmTokensCol().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a82d" ) ) {
				CFBamNumberDefBuff[] subList
					= schema.getTableNumberDef().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a82e" ) ) {
				CFBamNumberTypeBuff[] subList
					= schema.getTableNumberType().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a861" ) ) {
				CFBamNumberColBuff[] subList
					= schema.getTableNumberCol().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a838" ) ) {
				CFBamStringDefBuff[] subList
					= schema.getTableStringDef().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a839" ) ) {
				CFBamStringTypeBuff[] subList
					= schema.getTableStringType().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a862" ) ) {
				CFBamStringColBuff[] subList
					= schema.getTableStringCol().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83a" ) ) {
				CFBamTZDateDefBuff[] subList
					= schema.getTableTZDateDef().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83b" ) ) {
				CFBamTZDateTypeBuff[] subList
					= schema.getTableTZDateType().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a863" ) ) {
				CFBamTZDateColBuff[] subList
					= schema.getTableTZDateCol().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83c" ) ) {
				CFBamTZTimeDefBuff[] subList
					= schema.getTableTZTimeDef().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83d" ) ) {
				CFBamTZTimeTypeBuff[] subList
					= schema.getTableTZTimeType().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a864" ) ) {
				CFBamTZTimeColBuff[] subList
					= schema.getTableTZTimeCol().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83e" ) ) {
				CFBamTZTimestampDefBuff[] subList
					= schema.getTableTZTimestampDef().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83f" ) ) {
				CFBamTZTimestampTypeBuff[] subList
					= schema.getTableTZTimestampType().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a865" ) ) {
				CFBamTZTimestampColBuff[] subList
					= schema.getTableTZTimestampCol().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a841" ) ) {
				CFBamTextDefBuff[] subList
					= schema.getTableTextDef().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a842" ) ) {
				CFBamTextTypeBuff[] subList
					= schema.getTableTextType().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a866" ) ) {
				CFBamTextColBuff[] subList
					= schema.getTableTextCol().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a843" ) ) {
				CFBamTimeDefBuff[] subList
					= schema.getTableTimeDef().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a845" ) ) {
				CFBamTimeTypeBuff[] subList
					= schema.getTableTimeType().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a867" ) ) {
				CFBamTimeColBuff[] subList
					= schema.getTableTimeCol().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a846" ) ) {
				CFBamTimestampDefBuff[] subList
					= schema.getTableTimestampDef().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a847" ) ) {
				CFBamTimestampTypeBuff[] subList
					= schema.getTableTimestampType().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a868" ) ) {
				CFBamTimestampColBuff[] subList
					= schema.getTableTimestampCol().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a848" ) ) {
				CFBamTokenDefBuff[] subList
					= schema.getTableTokenDef().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a849" ) ) {
				CFBamTokenTypeBuff[] subList
					= schema.getTableTokenType().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a869" ) ) {
				CFBamTokenColBuff[] subList
					= schema.getTableTokenCol().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84a" ) ) {
				CFBamUInt16DefBuff[] subList
					= schema.getTableUInt16Def().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84b" ) ) {
				CFBamUInt16TypeBuff[] subList
					= schema.getTableUInt16Type().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a86a" ) ) {
				CFBamUInt16ColBuff[] subList
					= schema.getTableUInt16Col().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84c" ) ) {
				CFBamUInt32DefBuff[] subList
					= schema.getTableUInt32Def().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84d" ) ) {
				CFBamUInt32TypeBuff[] subList
					= schema.getTableUInt32Type().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a86b" ) ) {
				CFBamUInt32ColBuff[] subList
					= schema.getTableUInt32Col().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84e" ) ) {
				CFBamUInt64DefBuff[] subList
					= schema.getTableUInt64Def().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84f" ) ) {
				CFBamUInt64TypeBuff[] subList
					= schema.getTableUInt64Type().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a86c" ) ) {
				CFBamUInt64ColBuff[] subList
					= schema.getTableUInt64Col().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a850" ) ) {
				CFBamUuidDefBuff[] subList
					= schema.getTableUuidDef().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a851" ) ) {
				CFBamUuidTypeBuff[] subList
					= schema.getTableUuidType().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a86e" ) ) {
				CFBamUuidGenBuff[] subList
					= schema.getTableUuidGen().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a86d" ) ) {
				CFBamUuidColBuff[] subList
					= schema.getTableUuidCol().readBuffByScopeIdx( Authorization,
						TenantId,
						ScopeId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Did not expect ClassCode \"" + classCode + "\"" );
			}
		}
		int idx = 0;
		CFBamAtomBuff[] retBuff = new CFBamAtomBuff[ resultList.size() ];
		Iterator<CFBamAtomBuff> iter = resultList.iterator();
		while( iter.hasNext() ) {
			retBuff[idx++] = iter.next();
		}
		return( retBuff );

	}

	public CFBamAtomBuff[] readDerivedByDefSchemaIdx( CFSecAuthorization Authorization,
		Long DefSchemaTenantId,
		Long DefSchemaId )
	{
		final String S_ProcName = "readDerivedByDefSchemaIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ArrayList<String> classCodeList = new ArrayList<String>();
		String classCode;
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_atomdef_cc_by_defschemaidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadClassCodeByDefSchemaIdx == null ) {
				stmtReadClassCodeByDefSchemaIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadClassCodeByDefSchemaIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadClassCodeByDefSchemaIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadClassCodeByDefSchemaIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadClassCodeByDefSchemaIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadClassCodeByDefSchemaIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			if( DefSchemaTenantId != null ) {
				stmtReadClassCodeByDefSchemaIdx.setLong( argIdx++, DefSchemaTenantId.longValue() );
			}
			else {
				stmtReadClassCodeByDefSchemaIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( DefSchemaId != null ) {
				stmtReadClassCodeByDefSchemaIdx.setLong( argIdx++, DefSchemaId.longValue() );
			}
			else {
				stmtReadClassCodeByDefSchemaIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			resultSet = stmtReadClassCodeByDefSchemaIdx.executeQuery();
			while( resultSet.next() ) {
				classCode = resultSet.getString( 1 );
				classCodeList.add( classCode );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
		List<CFBamAtomBuff> resultList = new LinkedList<CFBamAtomBuff>();
		ListIterator<String> classCodeIter = classCodeList.listIterator();
		while( classCodeIter.hasNext() ) {
			classCode = classCodeIter.next();
			if( classCode.equals( "a80a" ) ) {
				CFBamAtomBuff[] subList
					= readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a80b" ) ) {
				CFBamBlobDefBuff[] subList
					= schema.getTableBlobDef().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a80c" ) ) {
				CFBamBlobTypeBuff[] subList
					= schema.getTableBlobType().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a852" ) ) {
				CFBamBlobColBuff[] subList
					= schema.getTableBlobCol().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a80d" ) ) {
				CFBamBoolDefBuff[] subList
					= schema.getTableBoolDef().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a80e" ) ) {
				CFBamBoolTypeBuff[] subList
					= schema.getTableBoolType().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a853" ) ) {
				CFBamBoolColBuff[] subList
					= schema.getTableBoolCol().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a815" ) ) {
				CFBamDateDefBuff[] subList
					= schema.getTableDateDef().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a816" ) ) {
				CFBamDateTypeBuff[] subList
					= schema.getTableDateType().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a854" ) ) {
				CFBamDateColBuff[] subList
					= schema.getTableDateCol().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a81c" ) ) {
				CFBamDoubleDefBuff[] subList
					= schema.getTableDoubleDef().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a81d" ) ) {
				CFBamDoubleTypeBuff[] subList
					= schema.getTableDoubleType().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a855" ) ) {
				CFBamDoubleColBuff[] subList
					= schema.getTableDoubleCol().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a81f" ) ) {
				CFBamFloatDefBuff[] subList
					= schema.getTableFloatDef().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a820" ) ) {
				CFBamFloatTypeBuff[] subList
					= schema.getTableFloatType().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a858" ) ) {
				CFBamFloatColBuff[] subList
					= schema.getTableFloatCol().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a823" ) ) {
				CFBamInt16DefBuff[] subList
					= schema.getTableInt16Def().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a824" ) ) {
				CFBamInt16TypeBuff[] subList
					= schema.getTableInt16Type().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a859" ) ) {
				CFBamId16GenBuff[] subList
					= schema.getTableId16Gen().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a856" ) ) {
				CFBamEnumDefBuff[] subList
					= schema.getTableEnumDef().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a857" ) ) {
				CFBamEnumTypeBuff[] subList
					= schema.getTableEnumType().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85c" ) ) {
				CFBamInt16ColBuff[] subList
					= schema.getTableInt16Col().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a825" ) ) {
				CFBamInt32DefBuff[] subList
					= schema.getTableInt32Def().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a826" ) ) {
				CFBamInt32TypeBuff[] subList
					= schema.getTableInt32Type().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85a" ) ) {
				CFBamId32GenBuff[] subList
					= schema.getTableId32Gen().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85d" ) ) {
				CFBamInt32ColBuff[] subList
					= schema.getTableInt32Col().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a827" ) ) {
				CFBamInt64DefBuff[] subList
					= schema.getTableInt64Def().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a828" ) ) {
				CFBamInt64TypeBuff[] subList
					= schema.getTableInt64Type().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85b" ) ) {
				CFBamId64GenBuff[] subList
					= schema.getTableId64Gen().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85e" ) ) {
				CFBamInt64ColBuff[] subList
					= schema.getTableInt64Col().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a829" ) ) {
				CFBamNmTokenDefBuff[] subList
					= schema.getTableNmTokenDef().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a82a" ) ) {
				CFBamNmTokenTypeBuff[] subList
					= schema.getTableNmTokenType().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85f" ) ) {
				CFBamNmTokenColBuff[] subList
					= schema.getTableNmTokenCol().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a82b" ) ) {
				CFBamNmTokensDefBuff[] subList
					= schema.getTableNmTokensDef().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a82c" ) ) {
				CFBamNmTokensTypeBuff[] subList
					= schema.getTableNmTokensType().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a860" ) ) {
				CFBamNmTokensColBuff[] subList
					= schema.getTableNmTokensCol().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a82d" ) ) {
				CFBamNumberDefBuff[] subList
					= schema.getTableNumberDef().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a82e" ) ) {
				CFBamNumberTypeBuff[] subList
					= schema.getTableNumberType().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a861" ) ) {
				CFBamNumberColBuff[] subList
					= schema.getTableNumberCol().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a838" ) ) {
				CFBamStringDefBuff[] subList
					= schema.getTableStringDef().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a839" ) ) {
				CFBamStringTypeBuff[] subList
					= schema.getTableStringType().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a862" ) ) {
				CFBamStringColBuff[] subList
					= schema.getTableStringCol().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83a" ) ) {
				CFBamTZDateDefBuff[] subList
					= schema.getTableTZDateDef().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83b" ) ) {
				CFBamTZDateTypeBuff[] subList
					= schema.getTableTZDateType().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a863" ) ) {
				CFBamTZDateColBuff[] subList
					= schema.getTableTZDateCol().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83c" ) ) {
				CFBamTZTimeDefBuff[] subList
					= schema.getTableTZTimeDef().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83d" ) ) {
				CFBamTZTimeTypeBuff[] subList
					= schema.getTableTZTimeType().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a864" ) ) {
				CFBamTZTimeColBuff[] subList
					= schema.getTableTZTimeCol().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83e" ) ) {
				CFBamTZTimestampDefBuff[] subList
					= schema.getTableTZTimestampDef().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83f" ) ) {
				CFBamTZTimestampTypeBuff[] subList
					= schema.getTableTZTimestampType().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a865" ) ) {
				CFBamTZTimestampColBuff[] subList
					= schema.getTableTZTimestampCol().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a841" ) ) {
				CFBamTextDefBuff[] subList
					= schema.getTableTextDef().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a842" ) ) {
				CFBamTextTypeBuff[] subList
					= schema.getTableTextType().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a866" ) ) {
				CFBamTextColBuff[] subList
					= schema.getTableTextCol().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a843" ) ) {
				CFBamTimeDefBuff[] subList
					= schema.getTableTimeDef().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a845" ) ) {
				CFBamTimeTypeBuff[] subList
					= schema.getTableTimeType().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a867" ) ) {
				CFBamTimeColBuff[] subList
					= schema.getTableTimeCol().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a846" ) ) {
				CFBamTimestampDefBuff[] subList
					= schema.getTableTimestampDef().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a847" ) ) {
				CFBamTimestampTypeBuff[] subList
					= schema.getTableTimestampType().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a868" ) ) {
				CFBamTimestampColBuff[] subList
					= schema.getTableTimestampCol().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a848" ) ) {
				CFBamTokenDefBuff[] subList
					= schema.getTableTokenDef().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a849" ) ) {
				CFBamTokenTypeBuff[] subList
					= schema.getTableTokenType().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a869" ) ) {
				CFBamTokenColBuff[] subList
					= schema.getTableTokenCol().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84a" ) ) {
				CFBamUInt16DefBuff[] subList
					= schema.getTableUInt16Def().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84b" ) ) {
				CFBamUInt16TypeBuff[] subList
					= schema.getTableUInt16Type().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a86a" ) ) {
				CFBamUInt16ColBuff[] subList
					= schema.getTableUInt16Col().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84c" ) ) {
				CFBamUInt32DefBuff[] subList
					= schema.getTableUInt32Def().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84d" ) ) {
				CFBamUInt32TypeBuff[] subList
					= schema.getTableUInt32Type().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a86b" ) ) {
				CFBamUInt32ColBuff[] subList
					= schema.getTableUInt32Col().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84e" ) ) {
				CFBamUInt64DefBuff[] subList
					= schema.getTableUInt64Def().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84f" ) ) {
				CFBamUInt64TypeBuff[] subList
					= schema.getTableUInt64Type().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a86c" ) ) {
				CFBamUInt64ColBuff[] subList
					= schema.getTableUInt64Col().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a850" ) ) {
				CFBamUuidDefBuff[] subList
					= schema.getTableUuidDef().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a851" ) ) {
				CFBamUuidTypeBuff[] subList
					= schema.getTableUuidType().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a86e" ) ) {
				CFBamUuidGenBuff[] subList
					= schema.getTableUuidGen().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a86d" ) ) {
				CFBamUuidColBuff[] subList
					= schema.getTableUuidCol().readBuffByDefSchemaIdx( Authorization,
						DefSchemaTenantId,
						DefSchemaId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Did not expect ClassCode \"" + classCode + "\"" );
			}
		}
		int idx = 0;
		CFBamAtomBuff[] retBuff = new CFBamAtomBuff[ resultList.size() ];
		Iterator<CFBamAtomBuff> iter = resultList.iterator();
		while( iter.hasNext() ) {
			retBuff[idx++] = iter.next();
		}
		return( retBuff );

	}

	public CFBamAtomBuff[] readDerivedByPrevIdx( CFSecAuthorization Authorization,
		Long PrevTenantId,
		Long PrevId )
	{
		final String S_ProcName = "readDerivedByPrevIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ArrayList<String> classCodeList = new ArrayList<String>();
		String classCode;
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_atomdef_cc_by_previdx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadClassCodeByPrevIdx == null ) {
				stmtReadClassCodeByPrevIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadClassCodeByPrevIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadClassCodeByPrevIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadClassCodeByPrevIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadClassCodeByPrevIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadClassCodeByPrevIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			if( PrevTenantId != null ) {
				stmtReadClassCodeByPrevIdx.setLong( argIdx++, PrevTenantId.longValue() );
			}
			else {
				stmtReadClassCodeByPrevIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( PrevId != null ) {
				stmtReadClassCodeByPrevIdx.setLong( argIdx++, PrevId.longValue() );
			}
			else {
				stmtReadClassCodeByPrevIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			resultSet = stmtReadClassCodeByPrevIdx.executeQuery();
			while( resultSet.next() ) {
				classCode = resultSet.getString( 1 );
				classCodeList.add( classCode );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
		List<CFBamAtomBuff> resultList = new LinkedList<CFBamAtomBuff>();
		ListIterator<String> classCodeIter = classCodeList.listIterator();
		while( classCodeIter.hasNext() ) {
			classCode = classCodeIter.next();
			if( classCode.equals( "a80a" ) ) {
				CFBamAtomBuff[] subList
					= readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a80b" ) ) {
				CFBamBlobDefBuff[] subList
					= schema.getTableBlobDef().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a80c" ) ) {
				CFBamBlobTypeBuff[] subList
					= schema.getTableBlobType().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a852" ) ) {
				CFBamBlobColBuff[] subList
					= schema.getTableBlobCol().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a80d" ) ) {
				CFBamBoolDefBuff[] subList
					= schema.getTableBoolDef().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a80e" ) ) {
				CFBamBoolTypeBuff[] subList
					= schema.getTableBoolType().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a853" ) ) {
				CFBamBoolColBuff[] subList
					= schema.getTableBoolCol().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a815" ) ) {
				CFBamDateDefBuff[] subList
					= schema.getTableDateDef().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a816" ) ) {
				CFBamDateTypeBuff[] subList
					= schema.getTableDateType().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a854" ) ) {
				CFBamDateColBuff[] subList
					= schema.getTableDateCol().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a81c" ) ) {
				CFBamDoubleDefBuff[] subList
					= schema.getTableDoubleDef().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a81d" ) ) {
				CFBamDoubleTypeBuff[] subList
					= schema.getTableDoubleType().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a855" ) ) {
				CFBamDoubleColBuff[] subList
					= schema.getTableDoubleCol().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a81f" ) ) {
				CFBamFloatDefBuff[] subList
					= schema.getTableFloatDef().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a820" ) ) {
				CFBamFloatTypeBuff[] subList
					= schema.getTableFloatType().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a858" ) ) {
				CFBamFloatColBuff[] subList
					= schema.getTableFloatCol().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a823" ) ) {
				CFBamInt16DefBuff[] subList
					= schema.getTableInt16Def().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a824" ) ) {
				CFBamInt16TypeBuff[] subList
					= schema.getTableInt16Type().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a859" ) ) {
				CFBamId16GenBuff[] subList
					= schema.getTableId16Gen().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a856" ) ) {
				CFBamEnumDefBuff[] subList
					= schema.getTableEnumDef().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a857" ) ) {
				CFBamEnumTypeBuff[] subList
					= schema.getTableEnumType().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85c" ) ) {
				CFBamInt16ColBuff[] subList
					= schema.getTableInt16Col().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a825" ) ) {
				CFBamInt32DefBuff[] subList
					= schema.getTableInt32Def().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a826" ) ) {
				CFBamInt32TypeBuff[] subList
					= schema.getTableInt32Type().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85a" ) ) {
				CFBamId32GenBuff[] subList
					= schema.getTableId32Gen().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85d" ) ) {
				CFBamInt32ColBuff[] subList
					= schema.getTableInt32Col().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a827" ) ) {
				CFBamInt64DefBuff[] subList
					= schema.getTableInt64Def().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a828" ) ) {
				CFBamInt64TypeBuff[] subList
					= schema.getTableInt64Type().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85b" ) ) {
				CFBamId64GenBuff[] subList
					= schema.getTableId64Gen().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85e" ) ) {
				CFBamInt64ColBuff[] subList
					= schema.getTableInt64Col().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a829" ) ) {
				CFBamNmTokenDefBuff[] subList
					= schema.getTableNmTokenDef().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a82a" ) ) {
				CFBamNmTokenTypeBuff[] subList
					= schema.getTableNmTokenType().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85f" ) ) {
				CFBamNmTokenColBuff[] subList
					= schema.getTableNmTokenCol().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a82b" ) ) {
				CFBamNmTokensDefBuff[] subList
					= schema.getTableNmTokensDef().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a82c" ) ) {
				CFBamNmTokensTypeBuff[] subList
					= schema.getTableNmTokensType().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a860" ) ) {
				CFBamNmTokensColBuff[] subList
					= schema.getTableNmTokensCol().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a82d" ) ) {
				CFBamNumberDefBuff[] subList
					= schema.getTableNumberDef().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a82e" ) ) {
				CFBamNumberTypeBuff[] subList
					= schema.getTableNumberType().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a861" ) ) {
				CFBamNumberColBuff[] subList
					= schema.getTableNumberCol().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a838" ) ) {
				CFBamStringDefBuff[] subList
					= schema.getTableStringDef().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a839" ) ) {
				CFBamStringTypeBuff[] subList
					= schema.getTableStringType().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a862" ) ) {
				CFBamStringColBuff[] subList
					= schema.getTableStringCol().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83a" ) ) {
				CFBamTZDateDefBuff[] subList
					= schema.getTableTZDateDef().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83b" ) ) {
				CFBamTZDateTypeBuff[] subList
					= schema.getTableTZDateType().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a863" ) ) {
				CFBamTZDateColBuff[] subList
					= schema.getTableTZDateCol().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83c" ) ) {
				CFBamTZTimeDefBuff[] subList
					= schema.getTableTZTimeDef().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83d" ) ) {
				CFBamTZTimeTypeBuff[] subList
					= schema.getTableTZTimeType().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a864" ) ) {
				CFBamTZTimeColBuff[] subList
					= schema.getTableTZTimeCol().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83e" ) ) {
				CFBamTZTimestampDefBuff[] subList
					= schema.getTableTZTimestampDef().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83f" ) ) {
				CFBamTZTimestampTypeBuff[] subList
					= schema.getTableTZTimestampType().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a865" ) ) {
				CFBamTZTimestampColBuff[] subList
					= schema.getTableTZTimestampCol().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a841" ) ) {
				CFBamTextDefBuff[] subList
					= schema.getTableTextDef().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a842" ) ) {
				CFBamTextTypeBuff[] subList
					= schema.getTableTextType().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a866" ) ) {
				CFBamTextColBuff[] subList
					= schema.getTableTextCol().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a843" ) ) {
				CFBamTimeDefBuff[] subList
					= schema.getTableTimeDef().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a845" ) ) {
				CFBamTimeTypeBuff[] subList
					= schema.getTableTimeType().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a867" ) ) {
				CFBamTimeColBuff[] subList
					= schema.getTableTimeCol().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a846" ) ) {
				CFBamTimestampDefBuff[] subList
					= schema.getTableTimestampDef().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a847" ) ) {
				CFBamTimestampTypeBuff[] subList
					= schema.getTableTimestampType().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a868" ) ) {
				CFBamTimestampColBuff[] subList
					= schema.getTableTimestampCol().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a848" ) ) {
				CFBamTokenDefBuff[] subList
					= schema.getTableTokenDef().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a849" ) ) {
				CFBamTokenTypeBuff[] subList
					= schema.getTableTokenType().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a869" ) ) {
				CFBamTokenColBuff[] subList
					= schema.getTableTokenCol().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84a" ) ) {
				CFBamUInt16DefBuff[] subList
					= schema.getTableUInt16Def().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84b" ) ) {
				CFBamUInt16TypeBuff[] subList
					= schema.getTableUInt16Type().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a86a" ) ) {
				CFBamUInt16ColBuff[] subList
					= schema.getTableUInt16Col().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84c" ) ) {
				CFBamUInt32DefBuff[] subList
					= schema.getTableUInt32Def().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84d" ) ) {
				CFBamUInt32TypeBuff[] subList
					= schema.getTableUInt32Type().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a86b" ) ) {
				CFBamUInt32ColBuff[] subList
					= schema.getTableUInt32Col().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84e" ) ) {
				CFBamUInt64DefBuff[] subList
					= schema.getTableUInt64Def().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84f" ) ) {
				CFBamUInt64TypeBuff[] subList
					= schema.getTableUInt64Type().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a86c" ) ) {
				CFBamUInt64ColBuff[] subList
					= schema.getTableUInt64Col().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a850" ) ) {
				CFBamUuidDefBuff[] subList
					= schema.getTableUuidDef().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a851" ) ) {
				CFBamUuidTypeBuff[] subList
					= schema.getTableUuidType().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a86e" ) ) {
				CFBamUuidGenBuff[] subList
					= schema.getTableUuidGen().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a86d" ) ) {
				CFBamUuidColBuff[] subList
					= schema.getTableUuidCol().readBuffByPrevIdx( Authorization,
						PrevTenantId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Did not expect ClassCode \"" + classCode + "\"" );
			}
		}
		int idx = 0;
		CFBamAtomBuff[] retBuff = new CFBamAtomBuff[ resultList.size() ];
		Iterator<CFBamAtomBuff> iter = resultList.iterator();
		while( iter.hasNext() ) {
			retBuff[idx++] = iter.next();
		}
		return( retBuff );

	}

	public CFBamAtomBuff[] readDerivedByNextIdx( CFSecAuthorization Authorization,
		Long NextTenantId,
		Long NextId )
	{
		final String S_ProcName = "readDerivedByNextIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ArrayList<String> classCodeList = new ArrayList<String>();
		String classCode;
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_atomdef_cc_by_nextidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadClassCodeByNextIdx == null ) {
				stmtReadClassCodeByNextIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadClassCodeByNextIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadClassCodeByNextIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadClassCodeByNextIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadClassCodeByNextIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadClassCodeByNextIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			if( NextTenantId != null ) {
				stmtReadClassCodeByNextIdx.setLong( argIdx++, NextTenantId.longValue() );
			}
			else {
				stmtReadClassCodeByNextIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( NextId != null ) {
				stmtReadClassCodeByNextIdx.setLong( argIdx++, NextId.longValue() );
			}
			else {
				stmtReadClassCodeByNextIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			resultSet = stmtReadClassCodeByNextIdx.executeQuery();
			while( resultSet.next() ) {
				classCode = resultSet.getString( 1 );
				classCodeList.add( classCode );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
		List<CFBamAtomBuff> resultList = new LinkedList<CFBamAtomBuff>();
		ListIterator<String> classCodeIter = classCodeList.listIterator();
		while( classCodeIter.hasNext() ) {
			classCode = classCodeIter.next();
			if( classCode.equals( "a80a" ) ) {
				CFBamAtomBuff[] subList
					= readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a80b" ) ) {
				CFBamBlobDefBuff[] subList
					= schema.getTableBlobDef().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a80c" ) ) {
				CFBamBlobTypeBuff[] subList
					= schema.getTableBlobType().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a852" ) ) {
				CFBamBlobColBuff[] subList
					= schema.getTableBlobCol().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a80d" ) ) {
				CFBamBoolDefBuff[] subList
					= schema.getTableBoolDef().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a80e" ) ) {
				CFBamBoolTypeBuff[] subList
					= schema.getTableBoolType().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a853" ) ) {
				CFBamBoolColBuff[] subList
					= schema.getTableBoolCol().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a815" ) ) {
				CFBamDateDefBuff[] subList
					= schema.getTableDateDef().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a816" ) ) {
				CFBamDateTypeBuff[] subList
					= schema.getTableDateType().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a854" ) ) {
				CFBamDateColBuff[] subList
					= schema.getTableDateCol().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a81c" ) ) {
				CFBamDoubleDefBuff[] subList
					= schema.getTableDoubleDef().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a81d" ) ) {
				CFBamDoubleTypeBuff[] subList
					= schema.getTableDoubleType().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a855" ) ) {
				CFBamDoubleColBuff[] subList
					= schema.getTableDoubleCol().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a81f" ) ) {
				CFBamFloatDefBuff[] subList
					= schema.getTableFloatDef().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a820" ) ) {
				CFBamFloatTypeBuff[] subList
					= schema.getTableFloatType().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a858" ) ) {
				CFBamFloatColBuff[] subList
					= schema.getTableFloatCol().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a823" ) ) {
				CFBamInt16DefBuff[] subList
					= schema.getTableInt16Def().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a824" ) ) {
				CFBamInt16TypeBuff[] subList
					= schema.getTableInt16Type().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a859" ) ) {
				CFBamId16GenBuff[] subList
					= schema.getTableId16Gen().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a856" ) ) {
				CFBamEnumDefBuff[] subList
					= schema.getTableEnumDef().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a857" ) ) {
				CFBamEnumTypeBuff[] subList
					= schema.getTableEnumType().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85c" ) ) {
				CFBamInt16ColBuff[] subList
					= schema.getTableInt16Col().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a825" ) ) {
				CFBamInt32DefBuff[] subList
					= schema.getTableInt32Def().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a826" ) ) {
				CFBamInt32TypeBuff[] subList
					= schema.getTableInt32Type().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85a" ) ) {
				CFBamId32GenBuff[] subList
					= schema.getTableId32Gen().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85d" ) ) {
				CFBamInt32ColBuff[] subList
					= schema.getTableInt32Col().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a827" ) ) {
				CFBamInt64DefBuff[] subList
					= schema.getTableInt64Def().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a828" ) ) {
				CFBamInt64TypeBuff[] subList
					= schema.getTableInt64Type().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85b" ) ) {
				CFBamId64GenBuff[] subList
					= schema.getTableId64Gen().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85e" ) ) {
				CFBamInt64ColBuff[] subList
					= schema.getTableInt64Col().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a829" ) ) {
				CFBamNmTokenDefBuff[] subList
					= schema.getTableNmTokenDef().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a82a" ) ) {
				CFBamNmTokenTypeBuff[] subList
					= schema.getTableNmTokenType().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85f" ) ) {
				CFBamNmTokenColBuff[] subList
					= schema.getTableNmTokenCol().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a82b" ) ) {
				CFBamNmTokensDefBuff[] subList
					= schema.getTableNmTokensDef().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a82c" ) ) {
				CFBamNmTokensTypeBuff[] subList
					= schema.getTableNmTokensType().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a860" ) ) {
				CFBamNmTokensColBuff[] subList
					= schema.getTableNmTokensCol().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a82d" ) ) {
				CFBamNumberDefBuff[] subList
					= schema.getTableNumberDef().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a82e" ) ) {
				CFBamNumberTypeBuff[] subList
					= schema.getTableNumberType().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a861" ) ) {
				CFBamNumberColBuff[] subList
					= schema.getTableNumberCol().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a838" ) ) {
				CFBamStringDefBuff[] subList
					= schema.getTableStringDef().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a839" ) ) {
				CFBamStringTypeBuff[] subList
					= schema.getTableStringType().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a862" ) ) {
				CFBamStringColBuff[] subList
					= schema.getTableStringCol().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83a" ) ) {
				CFBamTZDateDefBuff[] subList
					= schema.getTableTZDateDef().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83b" ) ) {
				CFBamTZDateTypeBuff[] subList
					= schema.getTableTZDateType().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a863" ) ) {
				CFBamTZDateColBuff[] subList
					= schema.getTableTZDateCol().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83c" ) ) {
				CFBamTZTimeDefBuff[] subList
					= schema.getTableTZTimeDef().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83d" ) ) {
				CFBamTZTimeTypeBuff[] subList
					= schema.getTableTZTimeType().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a864" ) ) {
				CFBamTZTimeColBuff[] subList
					= schema.getTableTZTimeCol().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83e" ) ) {
				CFBamTZTimestampDefBuff[] subList
					= schema.getTableTZTimestampDef().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83f" ) ) {
				CFBamTZTimestampTypeBuff[] subList
					= schema.getTableTZTimestampType().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a865" ) ) {
				CFBamTZTimestampColBuff[] subList
					= schema.getTableTZTimestampCol().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a841" ) ) {
				CFBamTextDefBuff[] subList
					= schema.getTableTextDef().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a842" ) ) {
				CFBamTextTypeBuff[] subList
					= schema.getTableTextType().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a866" ) ) {
				CFBamTextColBuff[] subList
					= schema.getTableTextCol().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a843" ) ) {
				CFBamTimeDefBuff[] subList
					= schema.getTableTimeDef().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a845" ) ) {
				CFBamTimeTypeBuff[] subList
					= schema.getTableTimeType().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a867" ) ) {
				CFBamTimeColBuff[] subList
					= schema.getTableTimeCol().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a846" ) ) {
				CFBamTimestampDefBuff[] subList
					= schema.getTableTimestampDef().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a847" ) ) {
				CFBamTimestampTypeBuff[] subList
					= schema.getTableTimestampType().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a868" ) ) {
				CFBamTimestampColBuff[] subList
					= schema.getTableTimestampCol().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a848" ) ) {
				CFBamTokenDefBuff[] subList
					= schema.getTableTokenDef().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a849" ) ) {
				CFBamTokenTypeBuff[] subList
					= schema.getTableTokenType().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a869" ) ) {
				CFBamTokenColBuff[] subList
					= schema.getTableTokenCol().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84a" ) ) {
				CFBamUInt16DefBuff[] subList
					= schema.getTableUInt16Def().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84b" ) ) {
				CFBamUInt16TypeBuff[] subList
					= schema.getTableUInt16Type().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a86a" ) ) {
				CFBamUInt16ColBuff[] subList
					= schema.getTableUInt16Col().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84c" ) ) {
				CFBamUInt32DefBuff[] subList
					= schema.getTableUInt32Def().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84d" ) ) {
				CFBamUInt32TypeBuff[] subList
					= schema.getTableUInt32Type().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a86b" ) ) {
				CFBamUInt32ColBuff[] subList
					= schema.getTableUInt32Col().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84e" ) ) {
				CFBamUInt64DefBuff[] subList
					= schema.getTableUInt64Def().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84f" ) ) {
				CFBamUInt64TypeBuff[] subList
					= schema.getTableUInt64Type().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a86c" ) ) {
				CFBamUInt64ColBuff[] subList
					= schema.getTableUInt64Col().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a850" ) ) {
				CFBamUuidDefBuff[] subList
					= schema.getTableUuidDef().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a851" ) ) {
				CFBamUuidTypeBuff[] subList
					= schema.getTableUuidType().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a86e" ) ) {
				CFBamUuidGenBuff[] subList
					= schema.getTableUuidGen().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a86d" ) ) {
				CFBamUuidColBuff[] subList
					= schema.getTableUuidCol().readBuffByNextIdx( Authorization,
						NextTenantId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Did not expect ClassCode \"" + classCode + "\"" );
			}
		}
		int idx = 0;
		CFBamAtomBuff[] retBuff = new CFBamAtomBuff[ resultList.size() ];
		Iterator<CFBamAtomBuff> iter = resultList.iterator();
		while( iter.hasNext() ) {
			retBuff[idx++] = iter.next();
		}
		return( retBuff );

	}

	public CFBamAtomBuff[] readDerivedByContPrevIdx( CFSecAuthorization Authorization,
		long TenantId,
		long ScopeId,
		Long PrevId )
	{
		final String S_ProcName = "readDerivedByContPrevIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ArrayList<String> classCodeList = new ArrayList<String>();
		String classCode;
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_atomdef_cc_by_contprevidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadClassCodeByContPrevIdx == null ) {
				stmtReadClassCodeByContPrevIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadClassCodeByContPrevIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadClassCodeByContPrevIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadClassCodeByContPrevIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadClassCodeByContPrevIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadClassCodeByContPrevIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadClassCodeByContPrevIdx.setLong( argIdx++, TenantId );
			stmtReadClassCodeByContPrevIdx.setLong( argIdx++, ScopeId );
			if( PrevId != null ) {
				stmtReadClassCodeByContPrevIdx.setLong( argIdx++, PrevId.longValue() );
			}
			else {
				stmtReadClassCodeByContPrevIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			resultSet = stmtReadClassCodeByContPrevIdx.executeQuery();
			while( resultSet.next() ) {
				classCode = resultSet.getString( 1 );
				classCodeList.add( classCode );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
		List<CFBamAtomBuff> resultList = new LinkedList<CFBamAtomBuff>();
		ListIterator<String> classCodeIter = classCodeList.listIterator();
		while( classCodeIter.hasNext() ) {
			classCode = classCodeIter.next();
			if( classCode.equals( "a80a" ) ) {
				CFBamAtomBuff[] subList
					= readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a80b" ) ) {
				CFBamBlobDefBuff[] subList
					= schema.getTableBlobDef().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a80c" ) ) {
				CFBamBlobTypeBuff[] subList
					= schema.getTableBlobType().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a852" ) ) {
				CFBamBlobColBuff[] subList
					= schema.getTableBlobCol().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a80d" ) ) {
				CFBamBoolDefBuff[] subList
					= schema.getTableBoolDef().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a80e" ) ) {
				CFBamBoolTypeBuff[] subList
					= schema.getTableBoolType().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a853" ) ) {
				CFBamBoolColBuff[] subList
					= schema.getTableBoolCol().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a815" ) ) {
				CFBamDateDefBuff[] subList
					= schema.getTableDateDef().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a816" ) ) {
				CFBamDateTypeBuff[] subList
					= schema.getTableDateType().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a854" ) ) {
				CFBamDateColBuff[] subList
					= schema.getTableDateCol().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a81c" ) ) {
				CFBamDoubleDefBuff[] subList
					= schema.getTableDoubleDef().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a81d" ) ) {
				CFBamDoubleTypeBuff[] subList
					= schema.getTableDoubleType().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a855" ) ) {
				CFBamDoubleColBuff[] subList
					= schema.getTableDoubleCol().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a81f" ) ) {
				CFBamFloatDefBuff[] subList
					= schema.getTableFloatDef().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a820" ) ) {
				CFBamFloatTypeBuff[] subList
					= schema.getTableFloatType().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a858" ) ) {
				CFBamFloatColBuff[] subList
					= schema.getTableFloatCol().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a823" ) ) {
				CFBamInt16DefBuff[] subList
					= schema.getTableInt16Def().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a824" ) ) {
				CFBamInt16TypeBuff[] subList
					= schema.getTableInt16Type().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a859" ) ) {
				CFBamId16GenBuff[] subList
					= schema.getTableId16Gen().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a856" ) ) {
				CFBamEnumDefBuff[] subList
					= schema.getTableEnumDef().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a857" ) ) {
				CFBamEnumTypeBuff[] subList
					= schema.getTableEnumType().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85c" ) ) {
				CFBamInt16ColBuff[] subList
					= schema.getTableInt16Col().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a825" ) ) {
				CFBamInt32DefBuff[] subList
					= schema.getTableInt32Def().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a826" ) ) {
				CFBamInt32TypeBuff[] subList
					= schema.getTableInt32Type().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85a" ) ) {
				CFBamId32GenBuff[] subList
					= schema.getTableId32Gen().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85d" ) ) {
				CFBamInt32ColBuff[] subList
					= schema.getTableInt32Col().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a827" ) ) {
				CFBamInt64DefBuff[] subList
					= schema.getTableInt64Def().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a828" ) ) {
				CFBamInt64TypeBuff[] subList
					= schema.getTableInt64Type().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85b" ) ) {
				CFBamId64GenBuff[] subList
					= schema.getTableId64Gen().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85e" ) ) {
				CFBamInt64ColBuff[] subList
					= schema.getTableInt64Col().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a829" ) ) {
				CFBamNmTokenDefBuff[] subList
					= schema.getTableNmTokenDef().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a82a" ) ) {
				CFBamNmTokenTypeBuff[] subList
					= schema.getTableNmTokenType().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85f" ) ) {
				CFBamNmTokenColBuff[] subList
					= schema.getTableNmTokenCol().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a82b" ) ) {
				CFBamNmTokensDefBuff[] subList
					= schema.getTableNmTokensDef().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a82c" ) ) {
				CFBamNmTokensTypeBuff[] subList
					= schema.getTableNmTokensType().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a860" ) ) {
				CFBamNmTokensColBuff[] subList
					= schema.getTableNmTokensCol().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a82d" ) ) {
				CFBamNumberDefBuff[] subList
					= schema.getTableNumberDef().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a82e" ) ) {
				CFBamNumberTypeBuff[] subList
					= schema.getTableNumberType().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a861" ) ) {
				CFBamNumberColBuff[] subList
					= schema.getTableNumberCol().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a838" ) ) {
				CFBamStringDefBuff[] subList
					= schema.getTableStringDef().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a839" ) ) {
				CFBamStringTypeBuff[] subList
					= schema.getTableStringType().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a862" ) ) {
				CFBamStringColBuff[] subList
					= schema.getTableStringCol().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83a" ) ) {
				CFBamTZDateDefBuff[] subList
					= schema.getTableTZDateDef().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83b" ) ) {
				CFBamTZDateTypeBuff[] subList
					= schema.getTableTZDateType().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a863" ) ) {
				CFBamTZDateColBuff[] subList
					= schema.getTableTZDateCol().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83c" ) ) {
				CFBamTZTimeDefBuff[] subList
					= schema.getTableTZTimeDef().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83d" ) ) {
				CFBamTZTimeTypeBuff[] subList
					= schema.getTableTZTimeType().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a864" ) ) {
				CFBamTZTimeColBuff[] subList
					= schema.getTableTZTimeCol().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83e" ) ) {
				CFBamTZTimestampDefBuff[] subList
					= schema.getTableTZTimestampDef().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83f" ) ) {
				CFBamTZTimestampTypeBuff[] subList
					= schema.getTableTZTimestampType().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a865" ) ) {
				CFBamTZTimestampColBuff[] subList
					= schema.getTableTZTimestampCol().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a841" ) ) {
				CFBamTextDefBuff[] subList
					= schema.getTableTextDef().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a842" ) ) {
				CFBamTextTypeBuff[] subList
					= schema.getTableTextType().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a866" ) ) {
				CFBamTextColBuff[] subList
					= schema.getTableTextCol().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a843" ) ) {
				CFBamTimeDefBuff[] subList
					= schema.getTableTimeDef().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a845" ) ) {
				CFBamTimeTypeBuff[] subList
					= schema.getTableTimeType().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a867" ) ) {
				CFBamTimeColBuff[] subList
					= schema.getTableTimeCol().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a846" ) ) {
				CFBamTimestampDefBuff[] subList
					= schema.getTableTimestampDef().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a847" ) ) {
				CFBamTimestampTypeBuff[] subList
					= schema.getTableTimestampType().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a868" ) ) {
				CFBamTimestampColBuff[] subList
					= schema.getTableTimestampCol().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a848" ) ) {
				CFBamTokenDefBuff[] subList
					= schema.getTableTokenDef().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a849" ) ) {
				CFBamTokenTypeBuff[] subList
					= schema.getTableTokenType().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a869" ) ) {
				CFBamTokenColBuff[] subList
					= schema.getTableTokenCol().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84a" ) ) {
				CFBamUInt16DefBuff[] subList
					= schema.getTableUInt16Def().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84b" ) ) {
				CFBamUInt16TypeBuff[] subList
					= schema.getTableUInt16Type().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a86a" ) ) {
				CFBamUInt16ColBuff[] subList
					= schema.getTableUInt16Col().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84c" ) ) {
				CFBamUInt32DefBuff[] subList
					= schema.getTableUInt32Def().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84d" ) ) {
				CFBamUInt32TypeBuff[] subList
					= schema.getTableUInt32Type().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a86b" ) ) {
				CFBamUInt32ColBuff[] subList
					= schema.getTableUInt32Col().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84e" ) ) {
				CFBamUInt64DefBuff[] subList
					= schema.getTableUInt64Def().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84f" ) ) {
				CFBamUInt64TypeBuff[] subList
					= schema.getTableUInt64Type().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a86c" ) ) {
				CFBamUInt64ColBuff[] subList
					= schema.getTableUInt64Col().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a850" ) ) {
				CFBamUuidDefBuff[] subList
					= schema.getTableUuidDef().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a851" ) ) {
				CFBamUuidTypeBuff[] subList
					= schema.getTableUuidType().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a86e" ) ) {
				CFBamUuidGenBuff[] subList
					= schema.getTableUuidGen().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a86d" ) ) {
				CFBamUuidColBuff[] subList
					= schema.getTableUuidCol().readBuffByContPrevIdx( Authorization,
						TenantId,
						ScopeId,
						PrevId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Did not expect ClassCode \"" + classCode + "\"" );
			}
		}
		int idx = 0;
		CFBamAtomBuff[] retBuff = new CFBamAtomBuff[ resultList.size() ];
		Iterator<CFBamAtomBuff> iter = resultList.iterator();
		while( iter.hasNext() ) {
			retBuff[idx++] = iter.next();
		}
		return( retBuff );

	}

	public CFBamAtomBuff[] readDerivedByContNextIdx( CFSecAuthorization Authorization,
		long TenantId,
		long ScopeId,
		Long NextId )
	{
		final String S_ProcName = "readDerivedByContNextIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ArrayList<String> classCodeList = new ArrayList<String>();
		String classCode;
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_atomdef_cc_by_contnextidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadClassCodeByContNextIdx == null ) {
				stmtReadClassCodeByContNextIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadClassCodeByContNextIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadClassCodeByContNextIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadClassCodeByContNextIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadClassCodeByContNextIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadClassCodeByContNextIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadClassCodeByContNextIdx.setLong( argIdx++, TenantId );
			stmtReadClassCodeByContNextIdx.setLong( argIdx++, ScopeId );
			if( NextId != null ) {
				stmtReadClassCodeByContNextIdx.setLong( argIdx++, NextId.longValue() );
			}
			else {
				stmtReadClassCodeByContNextIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			resultSet = stmtReadClassCodeByContNextIdx.executeQuery();
			while( resultSet.next() ) {
				classCode = resultSet.getString( 1 );
				classCodeList.add( classCode );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
		List<CFBamAtomBuff> resultList = new LinkedList<CFBamAtomBuff>();
		ListIterator<String> classCodeIter = classCodeList.listIterator();
		while( classCodeIter.hasNext() ) {
			classCode = classCodeIter.next();
			if( classCode.equals( "a80a" ) ) {
				CFBamAtomBuff[] subList
					= readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a80b" ) ) {
				CFBamBlobDefBuff[] subList
					= schema.getTableBlobDef().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a80c" ) ) {
				CFBamBlobTypeBuff[] subList
					= schema.getTableBlobType().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a852" ) ) {
				CFBamBlobColBuff[] subList
					= schema.getTableBlobCol().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a80d" ) ) {
				CFBamBoolDefBuff[] subList
					= schema.getTableBoolDef().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a80e" ) ) {
				CFBamBoolTypeBuff[] subList
					= schema.getTableBoolType().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a853" ) ) {
				CFBamBoolColBuff[] subList
					= schema.getTableBoolCol().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a815" ) ) {
				CFBamDateDefBuff[] subList
					= schema.getTableDateDef().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a816" ) ) {
				CFBamDateTypeBuff[] subList
					= schema.getTableDateType().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a854" ) ) {
				CFBamDateColBuff[] subList
					= schema.getTableDateCol().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a81c" ) ) {
				CFBamDoubleDefBuff[] subList
					= schema.getTableDoubleDef().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a81d" ) ) {
				CFBamDoubleTypeBuff[] subList
					= schema.getTableDoubleType().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a855" ) ) {
				CFBamDoubleColBuff[] subList
					= schema.getTableDoubleCol().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a81f" ) ) {
				CFBamFloatDefBuff[] subList
					= schema.getTableFloatDef().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a820" ) ) {
				CFBamFloatTypeBuff[] subList
					= schema.getTableFloatType().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a858" ) ) {
				CFBamFloatColBuff[] subList
					= schema.getTableFloatCol().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a823" ) ) {
				CFBamInt16DefBuff[] subList
					= schema.getTableInt16Def().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a824" ) ) {
				CFBamInt16TypeBuff[] subList
					= schema.getTableInt16Type().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a859" ) ) {
				CFBamId16GenBuff[] subList
					= schema.getTableId16Gen().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a856" ) ) {
				CFBamEnumDefBuff[] subList
					= schema.getTableEnumDef().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a857" ) ) {
				CFBamEnumTypeBuff[] subList
					= schema.getTableEnumType().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85c" ) ) {
				CFBamInt16ColBuff[] subList
					= schema.getTableInt16Col().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a825" ) ) {
				CFBamInt32DefBuff[] subList
					= schema.getTableInt32Def().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a826" ) ) {
				CFBamInt32TypeBuff[] subList
					= schema.getTableInt32Type().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85a" ) ) {
				CFBamId32GenBuff[] subList
					= schema.getTableId32Gen().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85d" ) ) {
				CFBamInt32ColBuff[] subList
					= schema.getTableInt32Col().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a827" ) ) {
				CFBamInt64DefBuff[] subList
					= schema.getTableInt64Def().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a828" ) ) {
				CFBamInt64TypeBuff[] subList
					= schema.getTableInt64Type().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85b" ) ) {
				CFBamId64GenBuff[] subList
					= schema.getTableId64Gen().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85e" ) ) {
				CFBamInt64ColBuff[] subList
					= schema.getTableInt64Col().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a829" ) ) {
				CFBamNmTokenDefBuff[] subList
					= schema.getTableNmTokenDef().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a82a" ) ) {
				CFBamNmTokenTypeBuff[] subList
					= schema.getTableNmTokenType().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a85f" ) ) {
				CFBamNmTokenColBuff[] subList
					= schema.getTableNmTokenCol().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a82b" ) ) {
				CFBamNmTokensDefBuff[] subList
					= schema.getTableNmTokensDef().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a82c" ) ) {
				CFBamNmTokensTypeBuff[] subList
					= schema.getTableNmTokensType().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a860" ) ) {
				CFBamNmTokensColBuff[] subList
					= schema.getTableNmTokensCol().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a82d" ) ) {
				CFBamNumberDefBuff[] subList
					= schema.getTableNumberDef().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a82e" ) ) {
				CFBamNumberTypeBuff[] subList
					= schema.getTableNumberType().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a861" ) ) {
				CFBamNumberColBuff[] subList
					= schema.getTableNumberCol().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a838" ) ) {
				CFBamStringDefBuff[] subList
					= schema.getTableStringDef().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a839" ) ) {
				CFBamStringTypeBuff[] subList
					= schema.getTableStringType().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a862" ) ) {
				CFBamStringColBuff[] subList
					= schema.getTableStringCol().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83a" ) ) {
				CFBamTZDateDefBuff[] subList
					= schema.getTableTZDateDef().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83b" ) ) {
				CFBamTZDateTypeBuff[] subList
					= schema.getTableTZDateType().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a863" ) ) {
				CFBamTZDateColBuff[] subList
					= schema.getTableTZDateCol().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83c" ) ) {
				CFBamTZTimeDefBuff[] subList
					= schema.getTableTZTimeDef().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83d" ) ) {
				CFBamTZTimeTypeBuff[] subList
					= schema.getTableTZTimeType().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a864" ) ) {
				CFBamTZTimeColBuff[] subList
					= schema.getTableTZTimeCol().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83e" ) ) {
				CFBamTZTimestampDefBuff[] subList
					= schema.getTableTZTimestampDef().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a83f" ) ) {
				CFBamTZTimestampTypeBuff[] subList
					= schema.getTableTZTimestampType().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a865" ) ) {
				CFBamTZTimestampColBuff[] subList
					= schema.getTableTZTimestampCol().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a841" ) ) {
				CFBamTextDefBuff[] subList
					= schema.getTableTextDef().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a842" ) ) {
				CFBamTextTypeBuff[] subList
					= schema.getTableTextType().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a866" ) ) {
				CFBamTextColBuff[] subList
					= schema.getTableTextCol().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a843" ) ) {
				CFBamTimeDefBuff[] subList
					= schema.getTableTimeDef().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a845" ) ) {
				CFBamTimeTypeBuff[] subList
					= schema.getTableTimeType().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a867" ) ) {
				CFBamTimeColBuff[] subList
					= schema.getTableTimeCol().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a846" ) ) {
				CFBamTimestampDefBuff[] subList
					= schema.getTableTimestampDef().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a847" ) ) {
				CFBamTimestampTypeBuff[] subList
					= schema.getTableTimestampType().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a868" ) ) {
				CFBamTimestampColBuff[] subList
					= schema.getTableTimestampCol().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a848" ) ) {
				CFBamTokenDefBuff[] subList
					= schema.getTableTokenDef().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a849" ) ) {
				CFBamTokenTypeBuff[] subList
					= schema.getTableTokenType().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a869" ) ) {
				CFBamTokenColBuff[] subList
					= schema.getTableTokenCol().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84a" ) ) {
				CFBamUInt16DefBuff[] subList
					= schema.getTableUInt16Def().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84b" ) ) {
				CFBamUInt16TypeBuff[] subList
					= schema.getTableUInt16Type().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a86a" ) ) {
				CFBamUInt16ColBuff[] subList
					= schema.getTableUInt16Col().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84c" ) ) {
				CFBamUInt32DefBuff[] subList
					= schema.getTableUInt32Def().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84d" ) ) {
				CFBamUInt32TypeBuff[] subList
					= schema.getTableUInt32Type().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a86b" ) ) {
				CFBamUInt32ColBuff[] subList
					= schema.getTableUInt32Col().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84e" ) ) {
				CFBamUInt64DefBuff[] subList
					= schema.getTableUInt64Def().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a84f" ) ) {
				CFBamUInt64TypeBuff[] subList
					= schema.getTableUInt64Type().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a86c" ) ) {
				CFBamUInt64ColBuff[] subList
					= schema.getTableUInt64Col().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a850" ) ) {
				CFBamUuidDefBuff[] subList
					= schema.getTableUuidDef().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a851" ) ) {
				CFBamUuidTypeBuff[] subList
					= schema.getTableUuidType().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a86e" ) ) {
				CFBamUuidGenBuff[] subList
					= schema.getTableUuidGen().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else if( classCode.equals( "a86d" ) ) {
				CFBamUuidColBuff[] subList
					= schema.getTableUuidCol().readBuffByContNextIdx( Authorization,
						TenantId,
						ScopeId,
						NextId );
				for( int subListIdx = 0; subListIdx < subList.length; subListIdx ++ )
				{
					resultList.add( subList[ subListIdx ] );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Did not expect ClassCode \"" + classCode + "\"" );
			}
		}
		int idx = 0;
		CFBamAtomBuff[] retBuff = new CFBamAtomBuff[ resultList.size() ];
		Iterator<CFBamAtomBuff> iter = resultList.iterator();
		while( iter.hasNext() ) {
			retBuff[idx++] = iter.next();
		}
		return( retBuff );

	}

	public CFBamAtomBuff readBuff( CFSecAuthorization Authorization,
		CFBamValuePKey PKey )
	{
		final String S_ProcName = "readBuff";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			long TenantId = PKey.getRequiredTenantId();
			long Id = PKey.getRequiredId();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_atomdef( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByPKey == null ) {
				stmtReadBuffByPKey = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByPKey.setLong( argIdx++, TenantId );
			stmtReadBuffByPKey.setLong( argIdx++, Id );
			resultSet = stmtReadBuffByPKey.executeQuery();
			if( resultSet.next() ) {
				CFBamAtomBuff buff = unpackAtomResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				return( buff );
			}
			else {
				return( null );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFBamAtomBuff lockBuff( CFSecAuthorization Authorization,
		CFBamValuePKey PKey )
	{
		final String S_ProcName = "lockBuff";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			long TenantId = PKey.getRequiredTenantId();
			long Id = PKey.getRequiredId();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_lock_atomdef( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtLockBuffByPKey == null ) {
				stmtLockBuffByPKey = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtLockBuffByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtLockBuffByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtLockBuffByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtLockBuffByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtLockBuffByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtLockBuffByPKey.setLong( argIdx++, TenantId );
			stmtLockBuffByPKey.setLong( argIdx++, Id );
			resultSet = stmtLockBuffByPKey.executeQuery();
			if( resultSet.next() ) {
				CFBamAtomBuff buff = unpackAtomResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				return( buff );
			}
			else {
				return( null );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFBamAtomBuff[] readAllBuff( CFSecAuthorization Authorization ) {
		final String S_ProcName = "readAllBuff";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_atomdef_all( ?, ?, ?, ?, ? )";
			if( stmtReadAllBuff == null ) {
				stmtReadAllBuff = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadAllBuff.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadAllBuff.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadAllBuff.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadAllBuff.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadAllBuff.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			resultSet = stmtReadAllBuff.executeQuery();
			List<CFBamAtomBuff> buffList = new LinkedList<CFBamAtomBuff>();
			while( resultSet.next() ) {
				CFBamAtomBuff buff = unpackAtomResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamAtomBuff[] retBuff = new CFBamAtomBuff[ buffList.size() ];
			Iterator<CFBamAtomBuff> iter = buffList.iterator();
			while( iter.hasNext() ) {
				retBuff[idx++] = iter.next();
			}
			return( retBuff );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFBamAtomBuff readBuffByIdIdx( CFSecAuthorization Authorization,
		long TenantId,
		long Id )
	{
		final String S_ProcName = "readBuffByIdIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_atomdef_by_ididx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByIdIdx == null ) {
				stmtReadBuffByIdIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByIdIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByIdIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByIdIdx.setLong( argIdx++, TenantId );
			stmtReadBuffByIdIdx.setLong( argIdx++, Id );
			resultSet = stmtReadBuffByIdIdx.executeQuery();
			if( resultSet.next() ) {
				CFBamAtomBuff buff = unpackAtomResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				return( buff );
			}
			else {
				return( null );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFBamAtomBuff readBuffByUNameIdx( CFSecAuthorization Authorization,
		long TenantId,
		long ScopeId,
		String Name )
	{
		final String S_ProcName = "readBuffByUNameIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_atomdef_by_unameidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByUNameIdx == null ) {
				stmtReadBuffByUNameIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByUNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByUNameIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByUNameIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByUNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByUNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByUNameIdx.setLong( argIdx++, TenantId );
			stmtReadBuffByUNameIdx.setLong( argIdx++, ScopeId );
			stmtReadBuffByUNameIdx.setString( argIdx++, Name );
			resultSet = stmtReadBuffByUNameIdx.executeQuery();
			if( resultSet.next() ) {
				CFBamAtomBuff buff = unpackAtomResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				return( buff );
			}
			else {
				return( null );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFBamAtomBuff[] readBuffByValTentIdx( CFSecAuthorization Authorization,
		long TenantId )
	{
		final String S_ProcName = "readBuffByValTentIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_atomdef_by_valtentidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByValTentIdx == null ) {
				stmtReadBuffByValTentIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByValTentIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByValTentIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByValTentIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByValTentIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByValTentIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByValTentIdx.setLong( argIdx++, TenantId );
			resultSet = stmtReadBuffByValTentIdx.executeQuery();
			List<CFBamAtomBuff> buffList = new LinkedList<CFBamAtomBuff>();
			while( resultSet.next() ) {
				CFBamAtomBuff buff = unpackAtomResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamAtomBuff[] retBuff = new CFBamAtomBuff[ buffList.size() ];
			Iterator<CFBamAtomBuff> iter = buffList.iterator();
			while( iter.hasNext() ) {
				retBuff[idx++] = iter.next();
			}
			return( retBuff );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFBamAtomBuff[] readBuffByScopeIdx( CFSecAuthorization Authorization,
		long TenantId,
		long ScopeId )
	{
		final String S_ProcName = "readBuffByScopeIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_atomdef_by_scopeidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByScopeIdx == null ) {
				stmtReadBuffByScopeIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByScopeIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByScopeIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByScopeIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByScopeIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByScopeIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByScopeIdx.setLong( argIdx++, TenantId );
			stmtReadBuffByScopeIdx.setLong( argIdx++, ScopeId );
			resultSet = stmtReadBuffByScopeIdx.executeQuery();
			List<CFBamAtomBuff> buffList = new LinkedList<CFBamAtomBuff>();
			while( resultSet.next() ) {
				CFBamAtomBuff buff = unpackAtomResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamAtomBuff[] retBuff = new CFBamAtomBuff[ buffList.size() ];
			Iterator<CFBamAtomBuff> iter = buffList.iterator();
			while( iter.hasNext() ) {
				retBuff[idx++] = iter.next();
			}
			return( retBuff );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFBamAtomBuff[] readBuffByDefSchemaIdx( CFSecAuthorization Authorization,
		Long DefSchemaTenantId,
		Long DefSchemaId )
	{
		final String S_ProcName = "readBuffByDefSchemaIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_atomdef_by_defschemaidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByDefSchemaIdx == null ) {
				stmtReadBuffByDefSchemaIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByDefSchemaIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByDefSchemaIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByDefSchemaIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByDefSchemaIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByDefSchemaIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			if( DefSchemaTenantId != null ) {
				stmtReadBuffByDefSchemaIdx.setLong( argIdx++, DefSchemaTenantId.longValue() );
			}
			else {
				stmtReadBuffByDefSchemaIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( DefSchemaId != null ) {
				stmtReadBuffByDefSchemaIdx.setLong( argIdx++, DefSchemaId.longValue() );
			}
			else {
				stmtReadBuffByDefSchemaIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			resultSet = stmtReadBuffByDefSchemaIdx.executeQuery();
			List<CFBamAtomBuff> buffList = new LinkedList<CFBamAtomBuff>();
			while( resultSet.next() ) {
				CFBamAtomBuff buff = unpackAtomResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamAtomBuff[] retBuff = new CFBamAtomBuff[ buffList.size() ];
			Iterator<CFBamAtomBuff> iter = buffList.iterator();
			while( iter.hasNext() ) {
				retBuff[idx++] = iter.next();
			}
			return( retBuff );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFBamAtomBuff[] readBuffByPrevIdx( CFSecAuthorization Authorization,
		Long PrevTenantId,
		Long PrevId )
	{
		final String S_ProcName = "readBuffByPrevIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_atomdef_by_previdx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByPrevIdx == null ) {
				stmtReadBuffByPrevIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByPrevIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByPrevIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByPrevIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByPrevIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByPrevIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			if( PrevTenantId != null ) {
				stmtReadBuffByPrevIdx.setLong( argIdx++, PrevTenantId.longValue() );
			}
			else {
				stmtReadBuffByPrevIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( PrevId != null ) {
				stmtReadBuffByPrevIdx.setLong( argIdx++, PrevId.longValue() );
			}
			else {
				stmtReadBuffByPrevIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			resultSet = stmtReadBuffByPrevIdx.executeQuery();
			List<CFBamAtomBuff> buffList = new LinkedList<CFBamAtomBuff>();
			while( resultSet.next() ) {
				CFBamAtomBuff buff = unpackAtomResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamAtomBuff[] retBuff = new CFBamAtomBuff[ buffList.size() ];
			Iterator<CFBamAtomBuff> iter = buffList.iterator();
			while( iter.hasNext() ) {
				retBuff[idx++] = iter.next();
			}
			return( retBuff );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFBamAtomBuff[] readBuffByNextIdx( CFSecAuthorization Authorization,
		Long NextTenantId,
		Long NextId )
	{
		final String S_ProcName = "readBuffByNextIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_atomdef_by_nextidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByNextIdx == null ) {
				stmtReadBuffByNextIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByNextIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByNextIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByNextIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByNextIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByNextIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			if( NextTenantId != null ) {
				stmtReadBuffByNextIdx.setLong( argIdx++, NextTenantId.longValue() );
			}
			else {
				stmtReadBuffByNextIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( NextId != null ) {
				stmtReadBuffByNextIdx.setLong( argIdx++, NextId.longValue() );
			}
			else {
				stmtReadBuffByNextIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			resultSet = stmtReadBuffByNextIdx.executeQuery();
			List<CFBamAtomBuff> buffList = new LinkedList<CFBamAtomBuff>();
			while( resultSet.next() ) {
				CFBamAtomBuff buff = unpackAtomResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamAtomBuff[] retBuff = new CFBamAtomBuff[ buffList.size() ];
			Iterator<CFBamAtomBuff> iter = buffList.iterator();
			while( iter.hasNext() ) {
				retBuff[idx++] = iter.next();
			}
			return( retBuff );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFBamAtomBuff[] readBuffByContPrevIdx( CFSecAuthorization Authorization,
		long TenantId,
		long ScopeId,
		Long PrevId )
	{
		final String S_ProcName = "readBuffByContPrevIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_atomdef_by_contprevidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByContPrevIdx == null ) {
				stmtReadBuffByContPrevIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByContPrevIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByContPrevIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByContPrevIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByContPrevIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByContPrevIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByContPrevIdx.setLong( argIdx++, TenantId );
			stmtReadBuffByContPrevIdx.setLong( argIdx++, ScopeId );
			if( PrevId != null ) {
				stmtReadBuffByContPrevIdx.setLong( argIdx++, PrevId.longValue() );
			}
			else {
				stmtReadBuffByContPrevIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			resultSet = stmtReadBuffByContPrevIdx.executeQuery();
			List<CFBamAtomBuff> buffList = new LinkedList<CFBamAtomBuff>();
			while( resultSet.next() ) {
				CFBamAtomBuff buff = unpackAtomResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamAtomBuff[] retBuff = new CFBamAtomBuff[ buffList.size() ];
			Iterator<CFBamAtomBuff> iter = buffList.iterator();
			while( iter.hasNext() ) {
				retBuff[idx++] = iter.next();
			}
			return( retBuff );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFBamAtomBuff[] readBuffByContNextIdx( CFSecAuthorization Authorization,
		long TenantId,
		long ScopeId,
		Long NextId )
	{
		final String S_ProcName = "readBuffByContNextIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_atomdef_by_contnextidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByContNextIdx == null ) {
				stmtReadBuffByContNextIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByContNextIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByContNextIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByContNextIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByContNextIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByContNextIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByContNextIdx.setLong( argIdx++, TenantId );
			stmtReadBuffByContNextIdx.setLong( argIdx++, ScopeId );
			if( NextId != null ) {
				stmtReadBuffByContNextIdx.setLong( argIdx++, NextId.longValue() );
			}
			else {
				stmtReadBuffByContNextIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			resultSet = stmtReadBuffByContNextIdx.executeQuery();
			List<CFBamAtomBuff> buffList = new LinkedList<CFBamAtomBuff>();
			while( resultSet.next() ) {
				CFBamAtomBuff buff = unpackAtomResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFBamAtomBuff[] retBuff = new CFBamAtomBuff[ buffList.size() ];
			Iterator<CFBamAtomBuff> iter = buffList.iterator();
			while( iter.hasNext() ) {
				retBuff[idx++] = iter.next();
			}
			return( retBuff );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void updateAtom( CFSecAuthorization Authorization,
		CFBamAtomBuff Buff )
	{
		final String S_ProcName = "updateAtom";
		ResultSet resultSet = null;
		try {
			String ClassCode = Buff.getClassCode();
			long TenantId = Buff.getRequiredTenantId();
			long ScopeId = Buff.getRequiredScopeId();
			long Id = Buff.getRequiredId();
			Long DefSchemaTenantId = Buff.getOptionalDefSchemaTenantId();
			Long DefSchemaId = Buff.getOptionalDefSchemaId();
			String Name = Buff.getRequiredName();
			String ShortName = Buff.getOptionalShortName();
			String Label = Buff.getOptionalLabel();
			String ShortDescription = Buff.getOptionalShortDescription();
			String Description = Buff.getOptionalDescription();
			String DefaultXmlValue = Buff.getOptionalDefaultXmlValue();
			boolean IsNullable = Buff.getRequiredIsNullable();
			Boolean GenerateId = Buff.getOptionalGenerateId();
			boolean ImplementsPolymorph = Buff.getRequiredImplementsPolymorph();
			Long PrevTenantId = Buff.getOptionalPrevTenantId();
			Long PrevId = Buff.getOptionalPrevId();
			Long NextTenantId = Buff.getOptionalNextTenantId();
			Long NextId = Buff.getOptionalNextId();
			String DbName = Buff.getOptionalDbName();
			int Revision = Buff.getRequiredRevision();
			Connection cnx = schema.getCnx();
			String sql =
				"select * from " + schema.getLowerDbSchemaName() + ".sp_update_atomdef( ?, ?, ?, ?, ?, ?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + " )";
			if( stmtUpdateByPKey == null ) {
				stmtUpdateByPKey = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtUpdateByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtUpdateByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtUpdateByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtUpdateByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtUpdateByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtUpdateByPKey.setString( argIdx++, ClassCode );
			stmtUpdateByPKey.setLong( argIdx++, TenantId );
			stmtUpdateByPKey.setLong( argIdx++, ScopeId );
			stmtUpdateByPKey.setLong( argIdx++, Id );
			if( DefSchemaTenantId != null ) {
				stmtUpdateByPKey.setLong( argIdx++, DefSchemaTenantId.longValue() );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( DefSchemaId != null ) {
				stmtUpdateByPKey.setLong( argIdx++, DefSchemaId.longValue() );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			stmtUpdateByPKey.setString( argIdx++, Name );
			if( ShortName != null ) {
				stmtUpdateByPKey.setString( argIdx++, ShortName );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( Label != null ) {
				stmtUpdateByPKey.setString( argIdx++, Label );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( ShortDescription != null ) {
				stmtUpdateByPKey.setString( argIdx++, ShortDescription );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( Description != null ) {
				stmtUpdateByPKey.setString( argIdx++, Description );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			if( DefaultXmlValue != null ) {
				stmtUpdateByPKey.setString( argIdx++, DefaultXmlValue );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			stmtUpdateByPKey.setBoolean( argIdx++, IsNullable );
			if( GenerateId != null ) {
				stmtUpdateByPKey.setBoolean( argIdx++, GenerateId.booleanValue() );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.BOOLEAN );
			}
			stmtUpdateByPKey.setBoolean( argIdx++, ImplementsPolymorph );
			if( PrevTenantId != null ) {
				stmtUpdateByPKey.setLong( argIdx++, PrevTenantId.longValue() );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( PrevId != null ) {
				stmtUpdateByPKey.setLong( argIdx++, PrevId.longValue() );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( NextTenantId != null ) {
				stmtUpdateByPKey.setLong( argIdx++, NextTenantId.longValue() );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( NextId != null ) {
				stmtUpdateByPKey.setLong( argIdx++, NextId.longValue() );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			if( DbName != null ) {
				stmtUpdateByPKey.setString( argIdx++, DbName );
			}
			else {
				stmtUpdateByPKey.setNull( argIdx++, java.sql.Types.VARCHAR );
			}
			stmtUpdateByPKey.setInt( argIdx++, Revision );
			resultSet = stmtUpdateByPKey.executeQuery();
			if( resultSet.next() ) {
				CFBamAtomBuff updatedBuff = unpackAtomResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				Buff.setRequiredScopeId( updatedBuff.getRequiredScopeId() );
				Buff.setOptionalDefSchemaTenantId( updatedBuff.getOptionalDefSchemaTenantId() );
				Buff.setOptionalDefSchemaId( updatedBuff.getOptionalDefSchemaId() );
				Buff.setRequiredName( updatedBuff.getRequiredName() );
				Buff.setOptionalShortName( updatedBuff.getOptionalShortName() );
				Buff.setOptionalLabel( updatedBuff.getOptionalLabel() );
				Buff.setOptionalShortDescription( updatedBuff.getOptionalShortDescription() );
				Buff.setOptionalDescription( updatedBuff.getOptionalDescription() );
				Buff.setOptionalDefaultXmlValue( updatedBuff.getOptionalDefaultXmlValue() );
				Buff.setRequiredIsNullable( updatedBuff.getRequiredIsNullable() );
				Buff.setOptionalGenerateId( updatedBuff.getOptionalGenerateId() );
				Buff.setRequiredImplementsPolymorph( updatedBuff.getRequiredImplementsPolymorph() );
				Buff.setOptionalPrevTenantId( updatedBuff.getOptionalPrevTenantId() );
				Buff.setOptionalPrevId( updatedBuff.getOptionalPrevId() );
				Buff.setOptionalNextTenantId( updatedBuff.getOptionalNextTenantId() );
				Buff.setOptionalNextId( updatedBuff.getOptionalNextId() );
				Buff.setOptionalDbName( updatedBuff.getOptionalDbName() );
				Buff.setRequiredRevision( updatedBuff.getRequiredRevision() );
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected a single-record response, " + resultSet.getRow() + " rows selected" );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteAtom( CFSecAuthorization Authorization,
		CFBamAtomBuff Buff )
	{
		final String S_ProcName = "deleteAtom";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			long TenantId = Buff.getRequiredTenantId();
			long Id = Buff.getRequiredId();

			String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_atomdef( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
			if( stmtDeleteByPKey == null ) {
				stmtDeleteByPKey = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtDeleteByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtDeleteByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtDeleteByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtDeleteByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtDeleteByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtDeleteByPKey.setLong( argIdx++, TenantId );
			stmtDeleteByPKey.setLong( argIdx++, Id );
			stmtDeleteByPKey.setInt( argIdx++, Buff.getRequiredRevision() );;
			resultSet = stmtDeleteByPKey.executeQuery();
			if( resultSet.next() ) {
				boolean deleteFlag = resultSet.getBoolean( 1 );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected 1 record result set to be returned by delete, not 0 rows" );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteAtomByIdIdx( CFSecAuthorization Authorization,
		long argTenantId,
		long argId )
	{
		final String S_ProcName = "deleteAtomByIdIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_atomdef_by_ididx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByIdIdx == null ) {
					stmtDeleteByIdIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByIdIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByIdIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByIdIdx.setLong( argIdx++, argTenantId );
				stmtDeleteByIdIdx.setLong( argIdx++, argId );
				resultSet = stmtDeleteByIdIdx.executeQuery();
				if( resultSet.next() ) {
					boolean deleteFlag = resultSet.getBoolean( 1 );
					if( resultSet.next() ) {
						throw new CFLibRuntimeException( getClass(),
							S_ProcName,
							"Did not expect multi-record response" );
					}
				}
				else {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 record result set to be returned by delete, not 0 rows" );
				}
			}
			catch( SQLException e ) {
				throw new CFLibDbException( getClass(),
					S_ProcName,
					e );
			}
			finally {
				if( resultSet != null ) {
					try {
						resultSet.close();
					}
					catch( SQLException e ) {
					}
					resultSet = null;
				}
			}
	}

	public void deleteAtomByIdIdx( CFSecAuthorization Authorization,
		CFBamValuePKey argKey )
	{
		deleteAtomByIdIdx( Authorization,
			argKey.getRequiredTenantId(),
			argKey.getRequiredId() );
	}

	public void deleteAtomByUNameIdx( CFSecAuthorization Authorization,
		long argTenantId,
		long argScopeId,
		String argName )
	{
		final String S_ProcName = "deleteAtomByUNameIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_atomdef_by_unameidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByUNameIdx == null ) {
					stmtDeleteByUNameIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByUNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByUNameIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByUNameIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByUNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByUNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByUNameIdx.setLong( argIdx++, argTenantId );
				stmtDeleteByUNameIdx.setLong( argIdx++, argScopeId );
				stmtDeleteByUNameIdx.setString( argIdx++, argName );
				resultSet = stmtDeleteByUNameIdx.executeQuery();
				if( resultSet.next() ) {
					boolean deleteFlag = resultSet.getBoolean( 1 );
					if( resultSet.next() ) {
						throw new CFLibRuntimeException( getClass(),
							S_ProcName,
							"Did not expect multi-record response" );
					}
				}
				else {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 record result set to be returned by delete, not 0 rows" );
				}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteAtomByUNameIdx( CFSecAuthorization Authorization,
		CFBamValueByUNameIdxKey argKey )
	{
		deleteAtomByUNameIdx( Authorization,
			argKey.getRequiredTenantId(),
			argKey.getRequiredScopeId(),
			argKey.getRequiredName() );
	}

	public void deleteAtomByValTentIdx( CFSecAuthorization Authorization,
		long argTenantId )
	{
		final String S_ProcName = "deleteAtomByValTentIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_atomdef_by_valtentidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByValTentIdx == null ) {
					stmtDeleteByValTentIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByValTentIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByValTentIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByValTentIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByValTentIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByValTentIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByValTentIdx.setLong( argIdx++, argTenantId );
				resultSet = stmtDeleteByValTentIdx.executeQuery();
				if( resultSet.next() ) {
					boolean deleteFlag = resultSet.getBoolean( 1 );
					if( resultSet.next() ) {
						throw new CFLibRuntimeException( getClass(),
							S_ProcName,
							"Did not expect multi-record response" );
					}
				}
				else {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 record result set to be returned by delete, not 0 rows" );
				}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteAtomByValTentIdx( CFSecAuthorization Authorization,
		CFBamValueByValTentIdxKey argKey )
	{
		deleteAtomByValTentIdx( Authorization,
			argKey.getRequiredTenantId() );
	}

	public void deleteAtomByScopeIdx( CFSecAuthorization Authorization,
		long argTenantId,
		long argScopeId )
	{
		final String S_ProcName = "deleteAtomByScopeIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_atomdef_by_scopeidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByScopeIdx == null ) {
					stmtDeleteByScopeIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByScopeIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByScopeIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByScopeIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByScopeIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByScopeIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByScopeIdx.setLong( argIdx++, argTenantId );
				stmtDeleteByScopeIdx.setLong( argIdx++, argScopeId );
				resultSet = stmtDeleteByScopeIdx.executeQuery();
				if( resultSet.next() ) {
					boolean deleteFlag = resultSet.getBoolean( 1 );
					if( resultSet.next() ) {
						throw new CFLibRuntimeException( getClass(),
							S_ProcName,
							"Did not expect multi-record response" );
					}
				}
				else {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 record result set to be returned by delete, not 0 rows" );
				}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteAtomByScopeIdx( CFSecAuthorization Authorization,
		CFBamValueByScopeIdxKey argKey )
	{
		deleteAtomByScopeIdx( Authorization,
			argKey.getRequiredTenantId(),
			argKey.getRequiredScopeId() );
	}

	public void deleteAtomByDefSchemaIdx( CFSecAuthorization Authorization,
		Long argDefSchemaTenantId,
		Long argDefSchemaId )
	{
		final String S_ProcName = "deleteAtomByDefSchemaIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_atomdef_by_defschemaidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByDefSchemaIdx == null ) {
					stmtDeleteByDefSchemaIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByDefSchemaIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByDefSchemaIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByDefSchemaIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByDefSchemaIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByDefSchemaIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				if( argDefSchemaTenantId != null ) {
					stmtDeleteByDefSchemaIdx.setLong( argIdx++, argDefSchemaTenantId.longValue() );
				}
				else {
					stmtDeleteByDefSchemaIdx.setNull( argIdx++, java.sql.Types.BIGINT );
				}
				if( argDefSchemaId != null ) {
					stmtDeleteByDefSchemaIdx.setLong( argIdx++, argDefSchemaId.longValue() );
				}
				else {
					stmtDeleteByDefSchemaIdx.setNull( argIdx++, java.sql.Types.BIGINT );
				}
				resultSet = stmtDeleteByDefSchemaIdx.executeQuery();
				if( resultSet.next() ) {
					boolean deleteFlag = resultSet.getBoolean( 1 );
					if( resultSet.next() ) {
						throw new CFLibRuntimeException( getClass(),
							S_ProcName,
							"Did not expect multi-record response" );
					}
				}
				else {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 record result set to be returned by delete, not 0 rows" );
				}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteAtomByDefSchemaIdx( CFSecAuthorization Authorization,
		CFBamValueByDefSchemaIdxKey argKey )
	{
		deleteAtomByDefSchemaIdx( Authorization,
			argKey.getOptionalDefSchemaTenantId(),
			argKey.getOptionalDefSchemaId() );
	}

	public void deleteAtomByPrevIdx( CFSecAuthorization Authorization,
		Long argPrevTenantId,
		Long argPrevId )
	{
		final String S_ProcName = "deleteAtomByPrevIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_atomdef_by_previdx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByPrevIdx == null ) {
					stmtDeleteByPrevIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByPrevIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByPrevIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByPrevIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByPrevIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByPrevIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				if( argPrevTenantId != null ) {
					stmtDeleteByPrevIdx.setLong( argIdx++, argPrevTenantId.longValue() );
				}
				else {
					stmtDeleteByPrevIdx.setNull( argIdx++, java.sql.Types.BIGINT );
				}
				if( argPrevId != null ) {
					stmtDeleteByPrevIdx.setLong( argIdx++, argPrevId.longValue() );
				}
				else {
					stmtDeleteByPrevIdx.setNull( argIdx++, java.sql.Types.BIGINT );
				}
				resultSet = stmtDeleteByPrevIdx.executeQuery();
				if( resultSet.next() ) {
					boolean deleteFlag = resultSet.getBoolean( 1 );
					if( resultSet.next() ) {
						throw new CFLibRuntimeException( getClass(),
							S_ProcName,
							"Did not expect multi-record response" );
					}
				}
				else {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 record result set to be returned by delete, not 0 rows" );
				}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteAtomByPrevIdx( CFSecAuthorization Authorization,
		CFBamValueByPrevIdxKey argKey )
	{
		deleteAtomByPrevIdx( Authorization,
			argKey.getOptionalPrevTenantId(),
			argKey.getOptionalPrevId() );
	}

	public void deleteAtomByNextIdx( CFSecAuthorization Authorization,
		Long argNextTenantId,
		Long argNextId )
	{
		final String S_ProcName = "deleteAtomByNextIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_atomdef_by_nextidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByNextIdx == null ) {
					stmtDeleteByNextIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByNextIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByNextIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByNextIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByNextIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByNextIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				if( argNextTenantId != null ) {
					stmtDeleteByNextIdx.setLong( argIdx++, argNextTenantId.longValue() );
				}
				else {
					stmtDeleteByNextIdx.setNull( argIdx++, java.sql.Types.BIGINT );
				}
				if( argNextId != null ) {
					stmtDeleteByNextIdx.setLong( argIdx++, argNextId.longValue() );
				}
				else {
					stmtDeleteByNextIdx.setNull( argIdx++, java.sql.Types.BIGINT );
				}
				resultSet = stmtDeleteByNextIdx.executeQuery();
				if( resultSet.next() ) {
					boolean deleteFlag = resultSet.getBoolean( 1 );
					if( resultSet.next() ) {
						throw new CFLibRuntimeException( getClass(),
							S_ProcName,
							"Did not expect multi-record response" );
					}
				}
				else {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 record result set to be returned by delete, not 0 rows" );
				}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteAtomByNextIdx( CFSecAuthorization Authorization,
		CFBamValueByNextIdxKey argKey )
	{
		deleteAtomByNextIdx( Authorization,
			argKey.getOptionalNextTenantId(),
			argKey.getOptionalNextId() );
	}

	public void deleteAtomByContPrevIdx( CFSecAuthorization Authorization,
		long argTenantId,
		long argScopeId,
		Long argPrevId )
	{
		final String S_ProcName = "deleteAtomByContPrevIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_atomdef_by_contprevidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByContPrevIdx == null ) {
					stmtDeleteByContPrevIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByContPrevIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByContPrevIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByContPrevIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByContPrevIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByContPrevIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByContPrevIdx.setLong( argIdx++, argTenantId );
				stmtDeleteByContPrevIdx.setLong( argIdx++, argScopeId );
				if( argPrevId != null ) {
					stmtDeleteByContPrevIdx.setLong( argIdx++, argPrevId.longValue() );
				}
				else {
					stmtDeleteByContPrevIdx.setNull( argIdx++, java.sql.Types.BIGINT );
				}
				resultSet = stmtDeleteByContPrevIdx.executeQuery();
				if( resultSet.next() ) {
					boolean deleteFlag = resultSet.getBoolean( 1 );
					if( resultSet.next() ) {
						throw new CFLibRuntimeException( getClass(),
							S_ProcName,
							"Did not expect multi-record response" );
					}
				}
				else {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 record result set to be returned by delete, not 0 rows" );
				}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteAtomByContPrevIdx( CFSecAuthorization Authorization,
		CFBamValueByContPrevIdxKey argKey )
	{
		deleteAtomByContPrevIdx( Authorization,
			argKey.getRequiredTenantId(),
			argKey.getRequiredScopeId(),
			argKey.getOptionalPrevId() );
	}

	public void deleteAtomByContNextIdx( CFSecAuthorization Authorization,
		long argTenantId,
		long argScopeId,
		Long argNextId )
	{
		final String S_ProcName = "deleteAtomByContNextIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_atomdef_by_contnextidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByContNextIdx == null ) {
					stmtDeleteByContNextIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByContNextIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByContNextIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByContNextIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByContNextIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByContNextIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByContNextIdx.setLong( argIdx++, argTenantId );
				stmtDeleteByContNextIdx.setLong( argIdx++, argScopeId );
				if( argNextId != null ) {
					stmtDeleteByContNextIdx.setLong( argIdx++, argNextId.longValue() );
				}
				else {
					stmtDeleteByContNextIdx.setNull( argIdx++, java.sql.Types.BIGINT );
				}
				resultSet = stmtDeleteByContNextIdx.executeQuery();
				if( resultSet.next() ) {
					boolean deleteFlag = resultSet.getBoolean( 1 );
					if( resultSet.next() ) {
						throw new CFLibRuntimeException( getClass(),
							S_ProcName,
							"Did not expect multi-record response" );
					}
				}
				else {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 record result set to be returned by delete, not 0 rows" );
				}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteAtomByContNextIdx( CFSecAuthorization Authorization,
		CFBamValueByContNextIdxKey argKey )
	{
		deleteAtomByContNextIdx( Authorization,
			argKey.getRequiredTenantId(),
			argKey.getRequiredScopeId(),
			argKey.getOptionalNextId() );
	}

	/**
	 *	Move the specified buffer up in the chain (i.e. to the previous position.)
	 *
	 *	@return	The refreshed buffer after it has been moved
	 */
	public CFBamAtomBuff moveBuffUp( CFSecAuthorization Authorization,
		long TenantId,
		long Id,
		int argRevision )
	{
		final String S_ProcName = "moveBuffUp";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_moveup_atomdef( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", ? )";
			if( stmtMoveUpBuff == null ) {
				stmtMoveUpBuff = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtMoveUpBuff.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtMoveUpBuff.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtMoveUpBuff.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtMoveUpBuff.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtMoveUpBuff.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtMoveUpBuff.setLong( argIdx++, TenantId );
			stmtMoveUpBuff.setLong( argIdx++, Id );
			stmtMoveUpBuff.setInt( argIdx++, argRevision );
			resultSet = stmtMoveUpBuff.executeQuery();
			if( resultSet.next() ) {
				CFBamAtomBuff buff = unpackAtomResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				return( buff );
			}
			else {
				return( null );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	/**
	 *	Move the specified buffer down in the chain (i.e. to the next position.)
	 *
	 *	@return	The refreshed buffer after it has been moved
	 */
	public CFBamAtomBuff moveBuffDown( CFSecAuthorization Authorization,
		long TenantId,
		long Id,
		int argRevision )
	{
		final String S_ProcName = "moveBuffDown";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_movedown_atomdef( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + ", ? )";
			if( stmtMoveDownBuff == null ) {
				stmtMoveDownBuff = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtMoveDownBuff.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtMoveDownBuff.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtMoveDownBuff.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtMoveDownBuff.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtMoveDownBuff.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtMoveDownBuff.setLong( argIdx++, TenantId );
			stmtMoveDownBuff.setLong( argIdx++, Id );
			stmtMoveDownBuff.setInt( argIdx++, argRevision );
			resultSet = stmtMoveDownBuff.executeQuery();
			if( resultSet.next() ) {
				CFBamAtomBuff buff = unpackAtomResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				return( buff );
			}
			else {
				return( null );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	/**
	 *	Release the prepared statements.
	 *	<p>
	 *	When the schema changes connections, the prepared statements
	 *	have to be released because they contain connection-specific
	 *	information for most databases.
	 */
	public void releasePreparedStatements() {
		final String S_ProcName = "releasePreparedStatements";
		S_sqlSelectAtomDistinctClassCode = null;
		S_sqlSelectAtomBuff = null;

		if( stmtReadBuffByPKey != null ) {
			try {
				stmtReadBuffByPKey.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByPKey = null;
		}
		if( stmtReadBuffAll != null ) {
			try {
				stmtReadBuffAll.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffAll = null;
		}
		if( stmtLockBuffByPKey != null ) {
			try {
				stmtLockBuffByPKey.close();
			}
			catch( SQLException e ) {
			}
			stmtLockBuffByPKey = null;
		}
		if( stmtCreateByPKey != null ) {
			try {
				stmtCreateByPKey.close();
			}
			catch( SQLException e ) {
			}
			stmtCreateByPKey = null;
		}
		if( stmtUpdateByPKey != null ) {
			try {
				stmtUpdateByPKey.close();
			}
			catch( SQLException e ) {
			}
			stmtUpdateByPKey = null;
		}
		if( stmtDeleteByPKey != null ) {
			try {
				stmtDeleteByPKey.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByPKey = null;
		}
		if( stmtDeleteByIdIdx != null ) {
			try {
				stmtDeleteByIdIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByIdIdx = null;
		}
		if( stmtDeleteByUNameIdx != null ) {
			try {
				stmtDeleteByUNameIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByUNameIdx = null;
		}
		if( stmtDeleteByValTentIdx != null ) {
			try {
				stmtDeleteByValTentIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByValTentIdx = null;
		}
		if( stmtDeleteByScopeIdx != null ) {
			try {
				stmtDeleteByScopeIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByScopeIdx = null;
		}
		if( stmtDeleteByDefSchemaIdx != null ) {
			try {
				stmtDeleteByDefSchemaIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByDefSchemaIdx = null;
		}
		if( stmtDeleteByPrevIdx != null ) {
			try {
				stmtDeleteByPrevIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByPrevIdx = null;
		}
		if( stmtDeleteByNextIdx != null ) {
			try {
				stmtDeleteByNextIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByNextIdx = null;
		}
		if( stmtDeleteByContPrevIdx != null ) {
			try {
				stmtDeleteByContPrevIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByContPrevIdx = null;
		}
		if( stmtDeleteByContNextIdx != null ) {
			try {
				stmtDeleteByContNextIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByContNextIdx = null;
		}
		if( stmtReadAllBuff != null ) {
			try {
				stmtReadAllBuff.close();
			}
			catch( SQLException e ) {
			}
			stmtReadAllBuff = null;
		}
		if( stmtReadDerivedClassCode != null ) {
			try {
				stmtReadDerivedClassCode.close();
			}
			catch( SQLException e ) {
			}
			stmtReadDerivedClassCode = null;
		}
		if( stmtLockDerivedClassCode != null ) {
			try {
				stmtLockDerivedClassCode.close();
			}
			catch( SQLException e ) {
			}
			stmtLockDerivedClassCode = null;
		}
		if( stmtReadAllClassCode != null ) {
			try {
				stmtReadAllClassCode.close();
			}
			catch( SQLException e ) {
			}
			stmtReadAllClassCode = null;
		}
		if( stmtReadClassCodeByIdIdx != null ) {
			try {
				stmtReadClassCodeByIdIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadClassCodeByIdIdx = null;
		}
		if( stmtReadBuffByIdIdx != null ) {
			try {
				stmtReadBuffByIdIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByIdIdx = null;
		}
		if( stmtReadClassCodeByUNameIdx != null ) {
			try {
				stmtReadClassCodeByUNameIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadClassCodeByUNameIdx = null;
		}
		if( stmtReadBuffByUNameIdx != null ) {
			try {
				stmtReadBuffByUNameIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByUNameIdx = null;
		}
		if( stmtReadClassCodeByValTentIdx != null ) {
			try {
				stmtReadClassCodeByValTentIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadClassCodeByValTentIdx = null;
		}
		if( stmtReadBuffByValTentIdx != null ) {
			try {
				stmtReadBuffByValTentIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByValTentIdx = null;
		}
		if( stmtReadClassCodeByScopeIdx != null ) {
			try {
				stmtReadClassCodeByScopeIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadClassCodeByScopeIdx = null;
		}
		if( stmtReadBuffByScopeIdx != null ) {
			try {
				stmtReadBuffByScopeIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByScopeIdx = null;
		}
		if( stmtReadClassCodeByDefSchemaIdx != null ) {
			try {
				stmtReadClassCodeByDefSchemaIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadClassCodeByDefSchemaIdx = null;
		}
		if( stmtReadBuffByDefSchemaIdx != null ) {
			try {
				stmtReadBuffByDefSchemaIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByDefSchemaIdx = null;
		}
		if( stmtReadClassCodeByPrevIdx != null ) {
			try {
				stmtReadClassCodeByPrevIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadClassCodeByPrevIdx = null;
		}
		if( stmtReadBuffByPrevIdx != null ) {
			try {
				stmtReadBuffByPrevIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByPrevIdx = null;
		}
		if( stmtReadClassCodeByNextIdx != null ) {
			try {
				stmtReadClassCodeByNextIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadClassCodeByNextIdx = null;
		}
		if( stmtReadBuffByNextIdx != null ) {
			try {
				stmtReadBuffByNextIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByNextIdx = null;
		}
		if( stmtReadClassCodeByContPrevIdx != null ) {
			try {
				stmtReadClassCodeByContPrevIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadClassCodeByContPrevIdx = null;
		}
		if( stmtReadBuffByContPrevIdx != null ) {
			try {
				stmtReadBuffByContPrevIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByContPrevIdx = null;
		}
		if( stmtReadClassCodeByContNextIdx != null ) {
			try {
				stmtReadClassCodeByContNextIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadClassCodeByContNextIdx = null;
		}
		if( stmtReadBuffByContNextIdx != null ) {
			try {
				stmtReadBuffByContNextIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByContNextIdx = null;
		}
		if( stmtMoveUpBuff != null ) {
			try {
				stmtMoveUpBuff.close();
			}
			catch( SQLException e ) {
			}
			stmtMoveUpBuff = null;
		}
		if( stmtMoveDownBuff != null ) {
			try {
				stmtMoveDownBuff.close();
			}
			catch( SQLException e ) {
			}
			stmtMoveDownBuff = null;
		}
	}
}
